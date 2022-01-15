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
import com.gb.kotlin_1728_2_1.databinding.FragmentDetailsBinding
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.model.WeatherDTO
import com.gb.kotlin_1728_2_1.utils.WeatherLoader


const val BUNDLE_KEY = "key"
const val BUNDLE_KEY_WEATHER = "key_weather_dto"
const val LATITUDE_EXTRA = "Latitude"
const val LONGITUDE_EXTRA = "Longitude"
const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"

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

    lateinit var localWeather: Weather
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            it.getParcelable<Weather>(BUNDLE_KEY)?.let {
                localWeather = it
                val intent = Intent(requireActivity(), DetailsService::class.java)
                intent.putExtra(LATITUDE_EXTRA, localWeather.city.lat)
                intent.putExtra(LONGITUDE_EXTRA, localWeather.city.lon)
                requireActivity().startService(intent)
                LocalBroadcastManager.getInstance(requireActivity())
                    .registerReceiver(receiver, IntentFilter(DETAILS_INTENT_FILTER))
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