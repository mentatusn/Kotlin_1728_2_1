package com.gb.kotlin_1728_2_1.lesson10

import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.FragmentGoogleMapsMainBinding
import com.gb.kotlin_1728_2_1.databinding.FragmentMainBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsFragment : Fragment() {

    private var _binding: FragmentGoogleMapsMainBinding? = null
    private val binding: FragmentGoogleMapsMainBinding
        get() {
            return _binding!!
        }


    private lateinit var map: GoogleMap
    private val markers = arrayListOf<Marker>()

    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        val m = LatLng(54.0, 37.0)
        //googleMap.addMarker(MarkerOptions().position(m).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(m))
        googleMap.setOnMapLongClickListener {
            getAddress(it)
            addMarker(it)
            drawLine()
        }

        googleMap.isMyLocationEnabled = true // TODO HW проверить есть ли разрешение на геолокацию
        googleMap.uiSettings.isZoomControlsEnabled = true


    }

    private fun drawLine() {
        val last = markers.size
        if (last > 1) {
            map.addPolyline(
                PolylineOptions().add(markers[last-1].position, markers[last - 2].position)
                    .color(Color.RED)
                    .width(5f)
            )
        }
    }

    private fun addMarker(location: LatLng) {
        val marker = map.addMarker(
            MarkerOptions().position(location)
                .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.ic_map_marker))
        )
        markers.add(marker!!)
    }

    private fun getAddress(location: LatLng) {
        Log.d("", " $location")
        Thread {
            val geocoder = Geocoder(requireContext())
            val listAddress = geocoder.getFromLocation(location.latitude, location.longitude, 1)
            requireActivity().runOnUiThread {
                binding.textAddress.text = listAddress[0].getAddressLine(0)
            }
        }.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoogleMapsMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        binding.buttonSearch.setOnClickListener {
            search()
        }
    }

    private fun search(){
        Thread {
            val geocoder = Geocoder(requireContext())
            val listAddress = geocoder.getFromLocationName(binding.searchAddress.text.toString(),1)
            requireActivity().runOnUiThread {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(listAddress[0].latitude,listAddress[0].longitude),10f))
                map.addMarker(MarkerOptions().position(LatLng(listAddress[0].latitude,listAddress[0].longitude)).title("") .icon(com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(R.drawable.ic_map_pin)))
                //addMarker(LatLng(listAddress[0].latitude,listAddress[0].longitude))
            }
        }.start()
    }
}