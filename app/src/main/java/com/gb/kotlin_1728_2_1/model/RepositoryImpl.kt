package com.gb.kotlin_1728_2_1.model

class RepositoryImpl:Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }

}