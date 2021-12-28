package com.gb.kotlin_1728_2_1.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gb.kotlin_1728_2_1.databinding.FragmentDetailsBinding
import com.gb.kotlin_1728_2_1.model.Weather


const val BUNDLE_KEY = "key"
class DetailsFragment : Fragment() {



    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            it.getParcelable<Weather>(BUNDLE_KEY)?.run {
                setWeatherData(this)
            }
        }

    }

    private fun setWeatherData(weather: Weather) {
        with(binding){
            with(weather){
                cityName.text = city.name
                        cityCoordinates.text =
                    "${weather.city.lat} ${city.lon}"
                temperatureValue.text = "${temperature}"
                feelsLikeValue.text = "${feelsLike}"
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(bundle:Bundle)=DetailsFragment().apply { arguments = bundle }
    }
}