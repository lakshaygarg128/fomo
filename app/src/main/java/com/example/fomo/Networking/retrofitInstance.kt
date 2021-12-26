package com.example.fomo.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {


        val api = Retrofit.Builder()
            .baseUrl("https://fomo-athenahack-hackathon.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(retrofitServiceWeather::class.java)


}