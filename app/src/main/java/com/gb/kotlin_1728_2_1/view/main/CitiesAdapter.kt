package com.gb.kotlin_1728_2_1.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.gb.kotlin_1728_2_1.databinding.FragmentMainRecyclerCityItemBinding
import com.gb.kotlin_1728_2_1.model.Weather

class CitiesAdapter(val listener: OnMyItemClickListener) :
    RecyclerView.Adapter<CitiesAdapter.MainViewHolder>() {

    private var weatherData: List<Weather> = listOf()


    fun setWeather(data: List<Weather>) {
        this.weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CitiesAdapter.MainViewHolder {
        val binding: FragmentMainRecyclerCityItemBinding =
            FragmentMainRecyclerCityItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MainViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: CitiesAdapter.MainViewHolder, position: Int) {
        holder.bind(this.weatherData[position])
    }
    override fun getItemCount(): Int {
        return weatherData.size
    }
    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
            with(FragmentMainRecyclerCityItemBinding.bind(itemView)){
                mainFragmentRecyclerItemTextView.text = weather.city.name
                root.setOnClickListener {
                    listener.onItemClick(weather)
                }
            }
            FragmentMainRecyclerCityItemBinding.bind(itemView).run {
                mainFragmentRecyclerItemTextView.text = weather.city.name
                root.setOnClickListener {
                    listener.onItemClick(weather)
                }
            }
        }
        
    }
}