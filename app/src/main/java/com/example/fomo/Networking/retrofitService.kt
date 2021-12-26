package com.example.fomo.Networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface retrofitService {

    @GET("/weather/details")
    fun getWeather(@Query("latitude") latitude:String,@Query("longitude") longitude:String): Call<Weather>
    @GET("/food/list")
    fun getDishes(@Query("weather") weather:String):Call<List<FoodItem>>
    @GET("food/list/regionWise")
    fun getLocationDishes(@Query("latitude") latitude:String,@Query("longitude") longitude:String): Call<List<FoodItem>>
}