package com.example.fomo.Networking

data class Weather(
    val city: String,
    val description: String,
    val feelslike: Int,
    val temperature: Int
)