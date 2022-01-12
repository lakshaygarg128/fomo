package com.example.fomo.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fomo.Networking.FoodItem
import com.example.fomo.Networking.retrofitInstance
import com.example.fomo.R
import com.example.fomo.database.FoodEntity
import com.example.fomo.databinding.FragmentLocationBinding
import com.example.fomo.utils.Constants
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


class LocationFragment : Fragment(R.layout.fragment_location), onRecipeClicked {

    private var Permission_id = 100
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding : FragmentLocationBinding
    private lateinit var  adapter :FoodAdapter
    var latitude : String = "28.6138954"
     var longitute : String = "77.2090057"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var foodViewModel: FoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        ActivityResultContracts.RequestPermission()
        foodViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(FoodViewModel::class.java)
    }
    private fun CheckPermisssion(){
        val task : Task<Location> = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            task.addOnSuccessListener {
                if(it!=null){
                    latitude=it.latitude.toString()
                    longitute=it.longitude.toString()
                }
            }
        }
        RequestPermission()
        return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationBinding.bind(view)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity as Activity)
        ActivityResultContracts.RequestPermission()
        RequestPermission()
        CheckPermisssion()

        fetchFoodData()

        binding.recylerViewfoodweather.layoutManager = LinearLayoutManager(activity as Context)
        adapter = FoodAdapter(this)
        binding.recylerViewfoodweather.adapter = adapter
        foodViewModel.allFoodItem.observe(viewLifecycleOwner, Observer {list->
            list.let {
                val favList:ArrayList<FoodItem> = ArrayList()
                for(element in it)
                {
                    favList.add(FoodItem(element.description,element.image,element.name,element.recipe))

                }

                adapter.addFavList(favList)
            }
        })
        if(activity!=null) {
            sharedPreferences =
                activity?.getSharedPreferences(Constants.SHARED_PREFERENCE, Context.MODE_PRIVATE)!!
            binding.cityTv.text=sharedPreferences.getString(Constants.CITY,null)
            binding.tempTv.text=sharedPreferences.getString(Constants.TEMP,null)
        }
    }

     fun fetchFoodData() {

        val instance = retrofitInstance.api.getLocationDishes(latitude,longitute)
        instance.enqueue(object : Callback<List<FoodItem>> {

            override fun onResponse(
                call: Call<List<FoodItem>>,
                response: Response<List<FoodItem>>
            ) {
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

        val intent = Intent(requireContext(), RecipeActivity::class.java)
        intent.putExtra("youtube", item.recipe)
        intent.putExtra("name", item.name)
        intent.putExtra("desc", item.description)
        startActivity(intent)
    }

    override fun onOrderClicked(item: FoodItem) {
        val intent = Intent(requireContext(),OrderActivity::class.java)
        startActivity(intent)
    }

    override fun insertFav(item: FoodEntity) {
        foodViewModel.insertFood(item)
    }

    override fun deleteFav(item: FoodEntity) {
        foodViewModel.deleteFood(item)
    }

}