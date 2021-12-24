package com.gb.kotlin_1728_2_1.viewmodel

import com.gb.kotlin_1728_2_1.model.Weather

sealed class AppState {
    data class Loading(val progress:Int):AppState()
    data class Success(val weatherData:List<Weather>):AppState()
    data class Error( val error:Throwable):AppState()
}