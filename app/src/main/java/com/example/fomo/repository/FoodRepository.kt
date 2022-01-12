package com.example.fomo.repository

import androidx.lifecycle.LiveData
import com.example.fomo.Networking.retrofitService
import com.example.fomo.database.FoodDao
import com.example.fomo.database.FoodEntity
import retrofit2.http.Query

class FoodRepository (private val foodDao: FoodDao,/*private val retrofitService: retrofitService*/) {

val allFoodItem:LiveData<List<FoodEntity>> = foodDao.getAllFood()

//    suspend fun getDishes(weather:String) = retrofitService.getDishes(weather)
//
//    suspend fun getWeather(latitude:String, longitude:String) = retrofitService.getWeather(latitude, longitude)
//
//    suspend fun getLocationDishes(latitude:String, longitude:String) = retrofitService.getLocationDishes(latitude, longitude)
    suspend fun insertFood(foodEntity: FoodEntity)
    {
        foodDao.insertFood(foodEntity)
    }
    suspend fun deleteFood(foodEntity: FoodEntity)
    {
        foodDao.deleteFood(foodEntity)
    }
    fun deleteAllFood()
    {
        foodDao.deleteAllFood()
    }

}