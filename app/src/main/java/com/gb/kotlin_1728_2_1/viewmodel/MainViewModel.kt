package com.gb.kotlin_1728_2_1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep

class MainViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()) : ViewModel() {

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun getWeatherFromServer(){
        Thread{
            liveData.postValue(AppState.Loading(0))
            sleep(5000)
            liveData.postValue(AppState.Success("Холодно","Очень хололдно"))
        }.start()
    }
}