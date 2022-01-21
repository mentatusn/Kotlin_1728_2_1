package com.gb.kotlin_1728_2_1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.model.getDefaultCity
import com.gb.kotlin_1728_2_1.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
) : ViewModel() {

    private val repositoryImpl: RepositoryImpl by lazy {
        RepositoryImpl()
    }
    fun getLiveData() = liveData

    fun getWeatherFromRemoteServer(lat: Double,lon: Double) {
        liveData.postValue(AppState.Loading(0))
        repositoryImpl.getWeatherFromServer(lat,lon,callback)
    }

    fun converterDTOtoModel(weatherDTO: WeatherDTO):List<Weather>{
       return listOf(Weather(getDefaultCity(),weatherDTO.fact.temp.toInt(),weatherDTO.fact.feelsLike.toInt(),weatherDTO.fact.icon))
    }

    private val callback = object : Callback<WeatherDTO> {
        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            if(response.isSuccessful){
                response.body()?.let {
                    liveData.postValue(AppState.Success(converterDTOtoModel(it)))
                }
            }else{
                // TODO HW
            }
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            // HW TODO("Not yet implemented")
        }
    }
}