package com.gb.kotlin_1728_2_1.view.main

import android.content.Context
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
import com.gb.kotlin_1728_2_1.view.MainActivity
import com.gb.kotlin_1728_2_1.view.details.BUNDLE_KEY
import com.gb.kotlin_1728_2_1.view.details.DetailsFragment
import com.gb.kotlin_1728_2_1.viewmodel.AppState
import com.gb.kotlin_1728_2_1.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainFragment : Fragment(), OnMyItemClickListener {


    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    private val adapter: MainFragmentAdapter by lazy {
        MainFragmentAdapter(this)
    }
    private var isRussian = true

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // обращаем внимание
        initView()
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> { renderData(it) })
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun initView() {
        with(binding) {
            mainFragmentRecyclerView.adapter = adapter
            mainFragmentFAB.setOnClickListener {
                sentRequest()
            }
        }
    }

    private fun sentRequest() {
        isRussian = !isRussian
        with(binding) {
            if (isRussian) {
                viewModel.getWeatherFromLocalSourceRus()
                mainFragmentFAB.setImageResource(R.drawable.ic_russia)
            } else {
                viewModel.getWeatherFromLocalSourceWorld()
                mainFragmentFAB.setImageResource(R.drawable.ic_earth)
            }
        }

    }

    private fun renderData(appState: AppState) {
        with(binding) {
            when (appState) {
                is AppState.Error -> {
                    mainFragmentLoadingLayout.visibility = View.GONE
                    Snackbar.make(root, "Error", Snackbar.LENGTH_LONG)
                        .setAction("Попробовать еще раз") {
                            sentRequest()
                        }.show()
                }
                is AppState.Loading -> {
                    mainFragmentLoadingLayout.visibility = View.VISIBLE
                }
                is AppState.Success -> {
                    mainFragmentLoadingLayout.visibility = View.GONE
                    adapter.setWeather(appState.weatherData)
                    binding.root.showSnackBarWithoutAction("Success showSnackBarWithoutAction",Snackbar.LENGTH_LONG)
                }
            }
        }
    }

    fun View.showSnackBarWithoutAction(text:String,length:Int){
        Snackbar.make(this,text,length).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onItemClick(weather: Weather) {

        activity?.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.container,
                    DetailsFragment.newInstance(
                        Bundle().apply {
                            putParcelable(BUNDLE_KEY, weather)
                        }
                    ))
                .addToBackStack("").commit()
        }
    }
}