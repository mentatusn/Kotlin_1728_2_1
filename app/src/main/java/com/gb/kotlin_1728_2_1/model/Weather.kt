package com.gb.kotlin_1728_2_1.model

import android.service.controls.templates.TemperatureControlTemplate

data class Weather(val city:City= getDefaultCity(), val temperature:Int=20, val feelsLike:Int=20)

data class City(val name:String,val lon:Double,val lat:Double)

fun getDefaultCity() = City("Москва",37.5,55.5)
