package com.example.fomo

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fomo.Networking.Weather
import com.example.fomo.Networking.retrofitInstance
import com.example.fomo.databinding.FragmentWeatherBinding
import com.example.fomo.utils.FoodAdapter
import com.example.fomo.utils.SessionManager
import com.example.fomo.utils.onRecipeClicked
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment(R.layout.fragment_weather), onRecipeClicked {
    private var Permission_id = 100
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var  adapter :FoodAdapter
    private lateinit var binding : FragmentWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity as Activity)
        ActivityResultContracts.RequestPermission()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)
        CheckPermisssion()
        Foodfetchdata()
        binding.RecylerViewFoodWeather.layoutManager = LinearLayoutManager(activity as Context)
        adapter = FoodAdapter(this)
        binding.RecylerViewFoodWeather.adapter = adapter
    }

    private fun Foodfetchdata() {
        // API CALL
    }

    private fun CheckPermisssion(){
        val task : Task<Location> = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            task.addOnSuccessListener {
                if(it!=null){
                    //Toast.makeText(activity as Context,"${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
                    apicall(it.latitude,it.longitude)
                }
            }
        }
        RequestPermission()
        return
    }

    private fun apicall(latitude: Double, longitude: Double) {
        val instance = retrofitInstance.api.getWeather(latitude.toString(),longitude.toString())
        instance.enqueue(object : Callback<Weather>{
            override fun onResponse(call: retrofit2.Call<Weather>, response: Response<Weather>) {
               val weather = response.body()
                if(weather!=null){
                    binding.cityTv.text = "${weather.city}"
                    binding.tempTv.text = "${weather.temperature}"
                    if (activity != null)
                    Toast.makeText(activity as Context,"${weather.city}  ${weather.temperature}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Toast.makeText(activity as Context,"Failed", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun RequestPermission(){
        ActivityCompat.requestPermissions(activity as Activity,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),Permission_id)
    }

    override fun onRecipeClicked(item: Food) {
        // on recipe Button clicked

    }


}