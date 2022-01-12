package com.example.fomo.ui

import android.app.Activity
import android.app.Application

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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fomo.Networking.FoodItem
import com.example.fomo.Networking.Weather
import com.example.fomo.Networking.retrofitInstance
import com.example.fomo.R
import com.example.fomo.database.FoodEntity
import com.example.fomo.databinding.FragmentWeatherBinding
import com.example.fomo.utils.Constants
import com.example.fomo.utils.FoodAdapter
import com.example.fomo.utils.FoodViewHolder
import com.example.fomo.utils.onRecipeClicked
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment(R.layout.fragment_weather), onRecipeClicked {
    private var Permission_id = 100
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var  adapter :FoodAdapter
    private lateinit var binding : FragmentWeatherBinding
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var foodViewModel: FoodViewModel

    //private val viewModel : FoodViewModel by viewModels<FoodViewModel>()

     var weatherres : String = "cold"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity as Activity)
        ActivityResultContracts.RequestPermission()
        foodViewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(FoodViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWeatherBinding.bind(view)
        RequestPermission()
        CheckPermisssion()
        Foodfetchdata()

        binding.recylerViewFoodWeather.layoutManager = LinearLayoutManager(activity as Context)
        adapter = FoodAdapter(this)
        binding.recylerViewFoodWeather.adapter = adapter
        foodViewModel.allFoodItem.observe(viewLifecycleOwner, Observer { list->
            list.let {
                val favList:ArrayList<FoodItem> =ArrayList()
                for(element in it)
                {
                    favList.add(FoodItem(element.description,element.image,element.name,element.recipe))
                }
                Log.d("FavWeather",favList.toString())
                adapter.addFavList(favList)

            }
        })
    }

    private  fun Foodfetchdata() {
        // API CALL

        Log.d("weather","$weatherres")
        val instance = retrofitInstance.api.getDishes(weatherres)
        instance.enqueue(object : Callback<List<FoodItem>>{

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

    private fun ResultWeather(feelstemp : Int ,
                               description : String )  {

        Log.d("weather descp","$description")
        if(description.equals("Sunny") || feelstemp>=28 || description=="Clear"){
            weatherres= "sunny"
            return
        }
        if(description=="Partly cloudy" || description.equals("Mist") || description=="Overcast" || description=="Cloudy" || description=="Rain"){
            weatherres= "rainy"
            return
        }
        if(feelstemp <= 20){
            weatherres= "cold"
            return
        }else{
            weatherres= "sunny"

        }

    }

    private fun CheckPermisssion(){
        val task : Task<Location> = fusedLocationProviderClient.lastLocation

        if(ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(activity as Context,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            task.addOnSuccessListener {
                if(it!=null){

                    apicall(it.latitude.toString(),it.longitude.toString())

                }else
                {
                    apicall()
                }
            }
        }

        RequestPermission()
        return
    }



    private fun apicall(latitude: String = "28.6138954", longitude: String="77.2090057") {
        val instance = retrofitInstance.api.getWeather(latitude,longitude)
        instance.enqueue(object : Callback<Weather>{
            override fun onResponse(call: retrofit2.Call<Weather>, response: Response<Weather>) {
               val weather = response.body()

                if(weather!=null){
                    binding.cityTv.text = "${weather.city}"
                    val temp="${weather.temperature}°C"
                    binding.tempTv.text = temp
                    if(activity!=null) {
                        sharedPreferences = activity?.getSharedPreferences(
                            Constants.SHARED_PREFERENCE,
                            Context.MODE_PRIVATE
                        )!!
                        sharedPreferences.edit().putString(Constants.CITY,weather.city).apply()
                        sharedPreferences.edit().putString(Constants.TEMP,temp).apply()
                    }
                    ResultWeather(weather.feelslike,weather.description)


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

    override fun onRecipeClicked(item: FoodItem) {

        val intent = Intent(requireContext(), RecipeActivity::class.java)
        intent.putExtra("youtube", item.recipe)
        intent.putExtra("name", item.name)
        intent.putExtra("desc", item.description)
        startActivity(intent)

    }

    override fun onOrderClicked(item: FoodItem) {
        val intent = Intent(requireContext(), OrderActivity::class.java)
        startActivity(intent)
    }

    override fun insertFav(item: FoodEntity) {
        foodViewModel.insertFood(item)
    }

    override fun deleteFav(item: FoodEntity) {
        foodViewModel.deleteFood(item)
    }

//    private fun apicall(latitude: Double, longitude: Double) {
//
//        Log.d("LogLatitude", latitude.toString())
//        Log.d("LogLongitude", longitude.toString())
//        viewModel.apply {
//            getWeather(latitude.toString(), longitude.toString())
//            weatherres.observe(viewLifecycleOwner) {
//                when (it) {
//                    is Resource.Success -> {
//                        progress.hideProgress()
//                        if (!it.value.error) {
//                            try {
//                                visitItems.clear()
//                                visitList.clear()
//                                visitList.addAll(it.value.data)
//                                for (i in visitList.indices) {
//                                    visitItems.add(it.value.data[i])
//                                }
//                                setupVisitRecycler(visitItems)
//                                binding.visitStatus.text = ""
//                            } catch (e: NullPointerException) {
//                                Toast.makeText(
//                                    requireActivity(),
//                                    "oops..! Something went wrong.",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        } else if (it.value.error) {
//                            if (flag == 0) {
//                                flag++
//                                Toast.makeText(
//                                    requireContext(),
//                                    it.value.message.toString(),
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                binding.visitStatus.text = "No Visits Today!"
//                            }
//                            visitItems.clear()
//                            setupVisitRecycler(visitItems)
//                        }
//                    }
//                    is Resource.Failure -> {
//                        progress.hideProgress()
//                        Toast.makeText(requireContext(), "Failed.", Toast.LENGTH_SHORT).show()
//                    }
//                    is Resource.Loading -> {
//                        if (progress.mDialog?.isShowing == true) {
//                            progress.hideProgress()
//                        } else {
//                            progress.showProgress(requireContext())
//                        }
//                    }
//                }
//            }
//        }
//    }


}