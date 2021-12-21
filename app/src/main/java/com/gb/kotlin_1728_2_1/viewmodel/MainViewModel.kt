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
        liveData.postValue(AppState.Loading(0))
        Thread{
            sleep(1000)
            val rand = (1..40).random()
            if(rand>25){
                liveData.postValue(AppState.Success("Жарко",""))
            }else{
                liveData.postValue(AppState.Success("Холодно",""))
            }

        }.start()
    }
}