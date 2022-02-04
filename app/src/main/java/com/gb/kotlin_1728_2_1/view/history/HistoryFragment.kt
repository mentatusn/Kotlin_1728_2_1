package com.gb.kotlin_1728_2_1.view.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.FragmentDetailsBinding
import com.gb.kotlin_1728_2_1.databinding.FragmentHistoryBinding
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.utils.BUNDLE_KEY
import com.gb.kotlin_1728_2_1.view.BaseFragment
import com.gb.kotlin_1728_2_1.view.details.DetailsFragment
import com.gb.kotlin_1728_2_1.view.main.CitiesAdapter
import com.gb.kotlin_1728_2_1.view.main.OnMyItemClickListener
import com.gb.kotlin_1728_2_1.viewmodel.AppState
import com.gb.kotlin_1728_2_1.viewmodel.HistoryViewModel
import com.gb.kotlin_1728_2_1.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate), OnMyItemClickListener {


    private val adapter: CitiesHistoryAdapter by lazy {
        CitiesHistoryAdapter(this)
    }

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // обращаем внимание
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
        viewModel.getAllHistory()
        binding.historyFragmentRecyclerview.adapter = adapter
    }



    private fun renderData(appState: AppState) {
        with(binding) {
            when (appState) {
                is AppState.Error -> {
                    // TODO HW
                }
                is AppState.Loading -> {}
                is AppState.Success -> {
                    adapter.setWeather(appState.weatherData)
                }
            }
        }
    }

    fun View.showSnackBarWithoutAction(text:String,length:Int){
        Snackbar.make(this,text,length).show()
    }


    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onItemClick(weather: Weather) {
        // можете что-то свое добавить
    }
}