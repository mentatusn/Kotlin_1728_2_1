package com.gb.kotlin_1728_2_1.repository

import com.gb.kotlin_1728_2_1.BuildConfig
import com.gb.kotlin_1728_2_1.model.*
import com.gb.kotlin_1728_2_1.room.App
import com.gb.kotlin_1728_2_1.room.HistoryWeatherEntity
import com.gb.kotlin_1728_2_1.utils.YANDEX_API_URL
import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryLocalImpl : RepositoryCitiesList, RepositoryHistoryWeather {

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
    override fun getAllHistoryWeather(): List<Weather> {
        return convertHistoryWeatherEntityToWeather(
            App.getHistoryWeatherDao().getAllHistoryWeather()
        )
    }

    override fun saveWeather(weather: Weather) {
        App.getHistoryWeatherDao().insert(
            convertWeatherToHistoryWeatherEntity(weather)
        )
    }

    private fun convertHistoryWeatherEntityToWeather(entityList: List<HistoryWeatherEntity>): List<Weather> {
        /*val newListWeather: MutableList<Weather> = mutableListOf()
        entityList.forEach {
            newListWeather.add(
                Weather(
                    City(it.city, 0.0, 0.0), it.temperature, it.feelsLike, it.icon
                )
            )
        }
        return newListWeather*/

        return entityList.map {
            Weather(
                City(it.city, 0.0, 0.0), it.temperature, it.feelsLike, it.icon
            )
        }

    }

    private fun convertWeatherToHistoryWeatherEntity(weather: Weather) =
        HistoryWeatherEntity(
            0,
            weather.city.name,
            weather.temperature,
            weather.feelsLike,
            weather.icon
        )

}