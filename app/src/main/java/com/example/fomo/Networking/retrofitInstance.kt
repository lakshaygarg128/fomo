package com.example.fomo.Networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitInstance {
    val api=Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).build().create(retrofitService::class.java)//.baseUrl()
}