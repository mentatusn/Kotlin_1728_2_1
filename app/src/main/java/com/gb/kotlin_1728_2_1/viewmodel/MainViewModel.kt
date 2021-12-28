package com.gb.kotlin_1728_2_1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.kotlin_1728_2_1.model.RepositoryImpl
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    private val repositoryImpl: RepositoryImpl by lazy {
        RepositoryImpl()
    }

    fun getLiveData() = liveData

    fun getWeatherFromLocalSourceRus() = getWeatherFromLocalServer(true)

    fun getWeatherFromLocalSourceWorld() = getWeatherFromLocalServer(false)

    fun getWeatherFromRemoteSource() = getWeatherFromLocalServer(true)//заглушка на 5 урок


    fun getWeatherFromLocalServer(isRussian: Boolean) {
        liveData.postValue(AppState.Loading(0))
        Thread {
            sleep(1000)
            val rand = (1..40).random()
            liveData.postValue(
                AppState.Success(
                    with(repositoryImpl) {
                        if (isRussian) {
                            getWeatherFromLocalStorageRus()
                        } else {
                            getWeatherFromLocalStorageWorld()
                        }
                    }
                )
            )
        }.start()
    }
}