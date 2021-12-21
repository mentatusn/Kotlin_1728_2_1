package com.gb.kotlin_1728_2_1.viewmodel

sealed class AppState {
    data class Loading(var progress:Int):AppState()
    data class Success(var weatherData:String,var weatherData2:String):AppState()
    data class Error( var error:Throwable):AppState()
}

data class Error2( var error:Throwable):AppState()