package com.gb.kotlin_1728_2_1.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.FragmentMainBinding
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.view.details.BUNDLE_KEY
import com.gb.kotlin_1728_2_1.view.details.DetailsFragment
import com.gb.kotlin_1728_2_1.viewmodel.AppState
import com.gb.kotlin_1728_2_1.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment(),OnMyItemClickListener {


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    private val adapter = MainFragmentAdapter(this)
    private var isRussian = true

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // обращаем внимание
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener {
            sentRequest()
        }
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun sentRequest() {
        isRussian = !isRussian
        if (isRussian) {
            viewModel.getWeatherFromLocalSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        } else {
            viewModel.getWeatherFromLocalSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                Snackbar.make(binding.root, "Error", Snackbar.LENGTH_LONG)
                    .setAction("Попробовать ещше раз") {
                        sentRequest()
                    }.show()
            }
            is AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                adapter.setWeather(appState.weatherData)
                Snackbar.make(
                    binding.root,
                    "Success",
                    Snackbar.LENGTH_LONG
                ).show()
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
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onItemClick(weather: Weather) {
        val bundle=Bundle()
        bundle.putParcelable(BUNDLE_KEY,weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.container,DetailsFragment.newInstance(bundle))
            .addToBackStack("").commit()
    }
}