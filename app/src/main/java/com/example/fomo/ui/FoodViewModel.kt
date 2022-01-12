package com.example.fomo.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.fomo.Networking.FoodItem
import com.example.fomo.Networking.Weather
import com.example.fomo.Networking.retrofitService
import com.example.fomo.database.FoodDatabase
import com.example.fomo.database.FoodEntity
import com.example.fomo.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class FoodViewModel constructor(application: Application/*,retrofitService: retrofitService*/)  : AndroidViewModel(application) {

val allFoodItem:LiveData<List<FoodEntity>>
val repository:FoodRepository
    init {
        val foodDao=FoodDatabase.getDatabase(application).getFoodDao()
        repository= FoodRepository(foodDao,/*retrofitService*/)
        allFoodItem=repository.allFoodItem
    }
//    private val _weatherres: MutableLiveData<Call<Weather>> = MutableLiveData()
//    val weatherres: LiveData<Call<Weather>>
//        get() = _weatherres
//
//    fun getWeather(latitude:String, longitude:String) = viewModelScope.launch {
//        Log.d("LogFoodViewModel", latitude.toString())
//        _weatherres.value = repository.getWeather(latitude, longitude)
//    }
//
//    private val _dishesres: MutableLiveData<Call<List<FoodItem>>> = MutableLiveData()
//    val dishesres: LiveData<Call<List<FoodItem>>>
//        get() = _dishesres
//
//    fun getDishes(weatherres: String) = viewModelScope.launch {
//        Log.d("LogFoodViewModel2", weatherres.toString())
//        _dishesres.value = repository.getDishes(weatherres)
//    }
//
//    private val _locationres: MutableLiveData<Call<List<FoodItem>>> = MutableLiveData()
//    val locationres: LiveData<Call<List<FoodItem>>>
//        get() = _dishesres
//
//    fun getLocationDishes(latitude:String, longitude:String) = viewModelScope.launch {
//        Log.d("LogFoodViewModel2", latitude.toString())
//        _locationres.value = repository.getLocationDishes(latitude, longitude)
//    }
    fun insertFood(foodEntity: FoodEntity)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.insertFood(foodEntity)
    }
    fun deleteFood(foodEntity: FoodEntity)=viewModelScope.launch(Dispatchers.IO){
        repository.deleteFood(foodEntity)
    }
    fun deleteAllFood()=viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllFood()
    }

}