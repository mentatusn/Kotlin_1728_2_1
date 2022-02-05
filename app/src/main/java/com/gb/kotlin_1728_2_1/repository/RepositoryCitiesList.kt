package com.gb.kotlin_1728_2_1.repository

import com.gb.kotlin_1728_2_1.model.Weather

interface RepositoryCitiesList {
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}

interface A1{
    fun move(){}
    fun drive(){}
    fun fly(){}
}