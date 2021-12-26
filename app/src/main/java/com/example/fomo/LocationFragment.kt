package com.example.fomo

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fomo.Networking.FoodItem
import com.example.fomo.Networking.retrofitInstance
import com.example.fomo.databinding.FragmentLocationBinding
import com.example.fomo.databinding.FragmentWeatherBinding
import com.example.fomo.utils.FoodAdapter
import com.example.fomo.utils.onRecipeClicked
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.jar.Manifest




class LocationFragment : Fragment(R.layout.fragment_location), onRecipeClicked {

    private var Permission_id = 100
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding : FragmentLocationBinding
    private lateinit var  adapter :FoodAdapter
    lateinit var longitute : String
    lateinit var latitude : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        ActivityResultContracts.RequestPermission()
    }
    private fun CheckPermisssion(){
        val task : Task<Location> = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            task.addOnSuccessListener {
                if(it!=null){
                    //Toast.makeText(activity as Context,"${it.latitude} ${it.longitude}",Toast.LENGTH_SHORT).show()
                    binding.weather.text = "Your location is ${it.longitude}"
                    latitude=it.latitude.toString()
                    longitute=it.longitude.toString()
                }
            }
        }
        ActivityResultContracts.RequestPermission()
        return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationBinding.bind(view)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity as Activity)
        ActivityResultContracts.RequestPermission()
        CheckPermisssion()
        GlobalScope.launch {
            fetchFoodData()
        }
        binding.recylerViewfoodweather.layoutManager = LinearLayoutManager(activity as Context)
        adapter = FoodAdapter(this)
        binding.recylerViewfoodweather.adapter = adapter

    }

    suspend  fun fetchFoodData() {
        delay(3000)
        val instance = retrofitInstance.api.getLocationDishes(latitude,longitute)
        instance.enqueue(object : Callback<List<FoodItem>> {

            override fun onResponse(
                call: Call<List<FoodItem>>,
                response: Response<List<FoodItem>>
            ) {
                Toast.makeText(
                    activity as Context,
                    "Food Api Passes",
                    Toast.LENGTH_SHORT
                ).show()
                val FoodItems = response.body()
                if (FoodItems != null) {
                    adapter.updatelist(FoodItems)
                }
            }

            override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                Toast.makeText(
                    activity as Context,
                    "FOOD API CALL FAILED",
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
        )
    }

    private fun RequestPermission(){
        ActivityCompat.requestPermissions(activity as Activity,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),Permission_id)
    }

    override fun onRecipeClicked(item: FoodItem) {
        Toast.makeText(activity as Context,"${item.name}",Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), RecipeActivity::class.java)
        intent.putExtra("youtube", item.recipe)
        intent.putExtra("name", item.name)
        intent.putExtra("desc", item.description)
        startActivity(intent)
    }

    override fun onOrderClicked(item: FoodItem) {

    }

}