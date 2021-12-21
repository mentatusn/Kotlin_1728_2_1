package com.gb.kotlin_1728_2_1.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.ActivityMainBinding
import com.gb.kotlin_1728_2_1.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    var _binding: FragmentMainBinding? = null
    private val binding:FragmentMainBinding
    get(){
        return _binding!!
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding.tv // null pointer
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }



    companion object {
        fun newInstance() = MainFragment()
    }
}