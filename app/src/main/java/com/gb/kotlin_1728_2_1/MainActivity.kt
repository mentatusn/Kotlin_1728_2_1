package com.gb.kotlin_1728_2_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gb.tets.Test

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO создать проект в гит
        setContentView(R.layout.activity_main)
        val person = Person(age=20,name="Pavel",bio="bidsewgerswg", bio2 ="sdgsgr")
        val person2 = Person("Pavel",12,"bio","bio2")
        //Log.d("mylogs", person.name)
        person.newProperty= ""
        Log.d("mylogs", person.newProperty)
        /* Log.d("mylogs", test)
        test = "NewTest"
        Log.d("mylogs", test)*/
        val test = Test()
    }
}
