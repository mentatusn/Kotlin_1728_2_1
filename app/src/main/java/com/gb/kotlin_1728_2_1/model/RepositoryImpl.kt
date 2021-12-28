package com.gb.kotlin_1728_2_1.model

class RepositoryImpl:Repository {
    override fun getWeatherFromServer()=Weather()

    override fun getWeatherFromLocalStorageRus()= getRussianCities()

    override fun getWeatherFromLocalStorageWorld()=getWorldCities()


}