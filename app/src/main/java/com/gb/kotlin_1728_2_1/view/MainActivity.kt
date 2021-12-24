package com.gb.kotlin_1728_2_1.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.ActivityMainBinding
import com.gb.kotlin_1728_2_1.lesson2.Car

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container,MainFragment.newInstance()).commit()
        }

        Car.main()
    }


}
