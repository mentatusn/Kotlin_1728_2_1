package com.gb.kotlin_1728_2_1.repository

import okhttp3.Callback


interface RepositoryDetails {
    fun getWeatherFromServer(url:String,callback: Callback)
}