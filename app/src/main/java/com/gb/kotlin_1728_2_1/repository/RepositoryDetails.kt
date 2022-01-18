package com.gb.kotlin_1728_2_1.repository

import com.gb.kotlin_1728_2_1.model.WeatherDTO
import okhttp3.Callback


interface RepositoryDetails {
    fun getWeatherFromServer(lat: Double,lon: Double,callback: retrofit2.Callback<WeatherDTO>)
}