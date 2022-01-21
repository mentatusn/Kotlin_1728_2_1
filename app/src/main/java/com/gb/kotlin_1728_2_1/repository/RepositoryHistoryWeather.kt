package com.gb.kotlin_1728_2_1.repository

import com.gb.kotlin_1728_2_1.model.Weather

interface RepositoryHistoryWeather {
    fun getAllHistoryWeather():List<Weather>
    fun saveWeather(weather: Weather)
}