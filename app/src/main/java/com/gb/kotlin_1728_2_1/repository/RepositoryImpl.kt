package com.gb.kotlin_1728_2_1.repository

import com.gb.kotlin_1728_2_1.BuildConfig
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.model.getRussianCities
import com.gb.kotlin_1728_2_1.model.getWorldCities
import com.gb.kotlin_1728_2_1.utils.YANDEX_API_KEY
import com.gb.kotlin_1728_2_1.utils.YANDEX_API_URL
import com.gb.kotlin_1728_2_1.utils.YANDEX_API_URL_END_POINT
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class RepositoryImpl: RepositoryCitiesList,RepositoryDetails {

    override fun getWeatherFromLocalStorageRus()= getRussianCities()

    override fun getWeatherFromLocalStorageWorld()= getWorldCities()

    override fun getWeatherFromServer(url: String, callback: Callback) {
        val builder= Request.Builder().apply {
            header(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
            url(url)
        }
        OkHttpClient().newCall(builder.build()).enqueue(callback)
    }
}