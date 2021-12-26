package com.example.fomo.Networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface retrofitServiceWeather {

    @GET("/weather/details")
    fun getWeather(@Query("latitude") latitude:String,@Query("longitude") longitude:String): Call<Weather>
}