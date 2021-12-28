package com.gb.kotlin_1728_2_1.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.MainRecyclerItemBinding
import com.gb.kotlin_1728_2_1.model.Weather

class MainFragmentAdapter(val listener: OnMyItemClickListener) :
    RecyclerView.Adapter<MainFragmentAdapter.MainViewHolder>() {

    private var weatherData: List<Weather> = listOf()


    fun setWeather(data: List<Weather>) {
        this.weatherData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainFragmentAdapter.MainViewHolder {
        val binding: MainRecyclerItemBinding =
            MainRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MainViewHolder(binding.root)
    }
    override fun onBindViewHolder(holder: MainFragmentAdapter.MainViewHolder, position: Int) {
        holder.bind(this.weatherData[position])
    }
    override fun getItemCount(): Int {
        return weatherData.size
    }
    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(weather: Weather) {
            with(MainRecyclerItemBinding.bind(itemView)){
                mainFragmentRecyclerItemTextView.text = weather.city.name
                root.setOnClickListener {
                    listener.onItemClick(weather)
                }
            }
            MainRecyclerItemBinding.bind(itemView).run {
                mainFragmentRecyclerItemTextView.text = weather.city.name
                root.setOnClickListener {
                    listener.onItemClick(weather)
                }
            }
        }
    }
}