package com.gb.kotlin_1728_2_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO создать проект в гит
        // TODO cherry-Pick ????
        setContentView(R.layout.activity_main)
        val person = Person(age = 20, name = "Pavel", bio = "bidsewgerswg", bio2 = "sdgsgr")
        val person2 = Person("Pavel", 12, "bio", "bio2")
        //Log.d("mylogs", person.name)
        person.newProperty = ""

        Log.d("mylogs", person.newProperty)
        /* Log.d("mylogs", test)
        test = "NewTest"
        Log.d("mylogs", test)*/
        Repository.getData()

        val tv = findViewById<TextView>(R.id.tv)
        tv.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })

        val callback = object : View.OnClickListener {
            override fun onClick(v: View?) {

            }
        }

        val cat = object {
            val name = "Kisa"
            val age = 3
        }

        Log.d("mylogs", " ${cat.name} ${cat.age}")

        Person.test
        Person.testFun()
        Person.testPublicStaticFinal


        var result = if (true) 1 else 2


        tv.text = "$result"
        Log.d("mylogs", "$result")
        result = when (WeatherType.SNOWY) {
            WeatherType.SUNNY -> 1
            WeatherType.RAINY -> 2
            WeatherType.CLOUDY -> 3
            WeatherType.MISTY -> 4
            WeatherType.SNOWY -> 5
            WeatherType.HAILY -> 6
            else -> {
                7
            }
        }
        val button = Button(this)
        if(button is View){

        }
        Log.d("mylogs", "$result")


        for(i in 1..20 step 2){
            //Log.d("mylogs", "$i")
        }

        for(i in 20 downTo 0){
            //Log.d("mylogs", "$i")
        }

        for(i in 0 until 20){
            //Log.d("mylogs", "$i")
        }

        repeat(20){
            Log.d("mylogs", "${it+1}")
        }


    }

    enum class WeatherType {
        SUNNY,
        RAINY,
        CLOUDY,
        MISTY,
        SNOWY,
        HAILY
    }

}
