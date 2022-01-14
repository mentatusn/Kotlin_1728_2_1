package com.gb.kotlin_1728_2_1.view.details

import android.app.IntentService
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.gb.kotlin_1728_2_1.BuildConfig
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.view.MainActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection


const val DETAILS_SERVICE_KEY_EXTRAS = "key_"

class DetailsService(name: String = "") : IntentService(name) {


    override fun onHandleIntent(intent: Intent?) {
        loadWeather(lat, lon)
    }

    fun loadWeather(lat: Double, lon: Double) {

        val url = URL("https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon")
        val httpsURLConnection = (url.openConnection() as HttpsURLConnection).apply {
            requestMethod = "GET"
            readTimeout = 2000
            addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
        }
        val bufferedReader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
        val weatherDTO: WeatherDTO? =
            Gson().fromJson(convertBufferToResult(bufferedReader), WeatherDTO::class.java)
        val intent = Intent().apply {
            putExtra(BUNDLE_KEY_WEATHER, weatherDTO)
        }
        Handler(Looper.getMainLooper()).post {
            startActivity(Intent(applicationContext,MainActivity::class.java).apply {
                putExtra(BUNDLE_KEY_WEATHER, weatherDTO)
            })
        }

    }

    private fun convertBufferToResult(bufferedReader: BufferedReader): String {
        return bufferedReader.lines().collect(Collectors.joining("\n"))
    }

}