package com.gb.kotlin_1728_2_1.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.gb.kotlin_1728_2_1.R
import com.gb.kotlin_1728_2_1.databinding.ActivityMainBinding
import com.gb.kotlin_1728_2_1.lesson4.LambdaJava
import com.gb.kotlin_1728_2_1.lesson4.LambdaKotlin
import com.gb.kotlin_1728_2_1.view.main.MainFragment

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(savedInstanceState==null){
            supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit()
        }

        binding.container.setOnClickListener { TODO("Not yet implemented") }
        //val l = LambdaJava()
        //l.main()
        val l = LambdaKotlin()
        l.main()
        l.setOnMyListener(callback)
        l.setOnMyListener(object :LambdaKotlin.OnMyListener{
            override fun onSomething(string: String?) {
                // dfgdrfhrd
            }
        })
        //l.setOnMyListener{callbackL}
        // FIXME не принимает лямбду
    }

    fun foo(pUs:String,myBlock1:(p1:Int,p2:Float)->String,myBlock2:(p1:Int,p2:Float)->String){

    }

    val callback = object :LambdaKotlin.OnMyListener{
        override fun onSomething(string: String?) {
            // dfgdrfhrd
        }
    }
    val callbackL = { string: String?->  }

}
