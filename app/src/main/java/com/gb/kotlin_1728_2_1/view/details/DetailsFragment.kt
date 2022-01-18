package com.gb.kotlin_1728_2_1.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.gb.kotlin_1728_2_1.BuildConfig
import com.gb.kotlin_1728_2_1.databinding.FragmentDetailsBinding
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.utils.*
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException


class DetailsFragment : Fragment(), WeatherLoader.OnWeatherLoaded {


    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                val weatherDTO = it.getParcelableExtra<WeatherDTO>(BUNDLE_KEY_WEATHER)
                if (weatherDTO != null) {
                    setWeatherData(weatherDTO)
                } else {
                    // HW ERROR
                }
            }

        }
    }
    private var client: OkHttpClient? = null

    private fun getWeather(){
        if(client==null)
            client = OkHttpClient()

        val builder= Request.Builder().apply {
            header(YANDEX_API_KEY,BuildConfig.WEATHER_API_KEY)
            url(YANDEX_API_URL+YANDEX_API_URL_END_POINT+"?lat=${localWeather.city.lat}&lon=${localWeather.city.lon}")
        }
        val request = builder.build()
        val call = client?.newCall(request)

      /*  Thread{
             task1
            call?.execute()
            task2
        }.start()
        call?.execute() // NetworkOnMainThreadException*/
        call?.enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                if(response.isSuccessful){
                    response.body()?.let {
                        val json = it.string()
                        requireActivity().runOnUiThread {
                            setWeatherData(Gson().fromJson(json, WeatherDTO::class.java))
                        }
                    }
                }else{
                    // TODO HW
                }
            }
        })
    }

    private lateinit var localWeather: Weather
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getParcelable<Weather>(BUNDLE_KEY)?.let {
                localWeather = it
                getWeather()
            }
        }


    }

    private fun setWeatherData(weatherDTO: WeatherDTO) {

        with(binding) {
            with(localWeather) {
                cityName.text = city.name
                cityCoordinates.text =
                    "${city.lat} ${city.lon}"
                temperatureValue.text = "${weatherDTO.fact.temp}"
                feelsLikeValue.text = "${weatherDTO.fact.feelsLike}"
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        LocalBroadcastManager.getInstance(requireActivity())
            .unregisterReceiver(receiver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(bundle: Bundle) = DetailsFragment().apply { arguments = bundle }
    }

    override fun onLoaded(weatherDTO: WeatherDTO?) {
        weatherDTO?.let {
            setWeatherData(weatherDTO)
        }
        Log.d("", "")
    }

    override fun onFailed() {
        //TODO HW
    }
}