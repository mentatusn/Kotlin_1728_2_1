package com.gb.kotlin_1728_2_1.view.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.FragmentMainBinding
import com.gb.kotlin_1728_2_1.model.City
import com.gb.kotlin_1728_2_1.model.Weather
import com.gb.kotlin_1728_2_1.utils.BUNDLE_KEY
import com.gb.kotlin_1728_2_1.view.BaseFragment
import com.gb.kotlin_1728_2_1.view.details.DetailsFragment
import com.gb.kotlin_1728_2_1.viewmodel.AppState
import com.gb.kotlin_1728_2_1.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), OnMyItemClickListener {


    private val adapter: CitiesAdapter by lazy {
        CitiesAdapter(this)
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
            mainFragmentFABLocation.setOnClickListener {
                checkPermission()
            }
        }
    }

    private fun checkPermission() {
        context?.let {
            when {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    getLocation()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    showDialogRatio()
                }
                else -> {
                    myRequestPermission()
                }
            }
        }
    }


    private val MIN_DISTANCE = 100f
    private val REFRESH_PERIOD = 60000L

    private fun showAddressDialog(address:String,location: Location){
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_address_title))
            .setMessage(address)
            .setPositiveButton(getString(R.string.dialog_address_get_weather)) { _, _ ->
                toDetails(Weather(City(address,location.latitude,location.longitude)))
            }
            .setNegativeButton("Не надо") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun getAddress(location: Location){
        Log.d(""," $location")
        Thread{
            val geocoder = Geocoder(requireContext())
            val listAddress=geocoder.getFromLocation(location.latitude,location.longitude,1)
            requireActivity().runOnUiThread {
                showAddressDialog(listAddress[0].getAddressLine(0),location)
            }
        }.start()
    }
    private val locationListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            getAddress(location)
        }
        override fun onProviderDisabled(provider: String) {
            super.onProviderDisabled(provider)
        }
        override fun onProviderEnabled(provider: String) {
            super.onProviderEnabled(provider)
        }
    }


    private fun getLocation(){
        activity?.let {
            if(ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )==PackageManager.PERMISSION_GRANTED){
                val locationManager = it.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    val providerGPS = locationManager.getProvider(LocationManager.GPS_PROVIDER)
                    providerGPS?.let {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            REFRESH_PERIOD,
                            MIN_DISTANCE,
                            locationListener
                        )
                    }
                }else{
                    val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    lastLocation?.let{
                        getAddress(it)
                    }
                }
            }else{

            }
        }
    }

    private fun showDialog(){

    }

    val REQUEST_CODE = 999
    private fun myRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {

            when {
                (grantResults[0] == PackageManager.PERMISSION_GRANTED) -> {
                    getLocation()
                }
                shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                    showDialogRatio()
                }
                else -> {
                    Log.d("", "КОНЕЦ")
                }
            }
        }
    }

    private fun showDialogRatio() {
        AlertDialog.Builder(requireContext())
            .setTitle("Доступ к геолокации") // TODO HW
            .setMessage(getString(R.string.dialog_message_no_gps))
            .setPositiveButton("Предоставить доступ") { _, _ ->
                myRequestPermission()
            }
            .setNegativeButton("Не надо") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()

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


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onItemClick(weather: Weather) {

        toDetails(weather)
    }

    private fun toDetails(weather: Weather) {
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