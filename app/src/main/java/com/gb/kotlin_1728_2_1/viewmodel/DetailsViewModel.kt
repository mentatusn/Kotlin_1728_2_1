package com.gb.kotlin_1728_2_1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.model.getDefaultCity
import com.gb.kotlin_1728_2_1.repository.RepositoryImpl
import com.gb.kotlin_1728_2_1.utils.YANDEX_API_URL
import com.gb.kotlin_1728_2_1.utils.YANDEX_API_URL_END_POINT
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Thread.sleep

class DetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    private val repositoryImpl: RepositoryImpl by lazy {
        RepositoryImpl()
    }
    fun getLiveData() = liveData

    fun getWeatherFromRemoteServer(lat: Double,lon: Double) {
        liveData.postValue(AppState.Loading(0))
        repositoryImpl.getWeatherFromServer(YANDEX_API_URL + YANDEX_API_URL_END_POINT +"?lat=${lat}&lon=${lon}",callback)
    }

    fun converterDTOtoModel(weatherDTO: WeatherDTO):List<Weather>{
       return listOf(Weather(getDefaultCity(),weatherDTO.fact.temp.toInt(),weatherDTO.fact.feelsLike.toInt()))
    }

    private val callback = object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            TODO("Not yet implemented")
        }

        override fun onResponse(call: Call, response: Response) {
            if(response.isSuccessful){
                response.body()?.let {
                    val json = it.string()
                    liveData.postValue(AppState.Success(converterDTOtoModel(Gson().fromJson(json, WeatherDTO::class.java))))
                }
            }else{
                // TODO HW
            }
        }
    }
}