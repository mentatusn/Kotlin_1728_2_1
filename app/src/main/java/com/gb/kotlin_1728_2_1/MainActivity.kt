package com.gb.kotlin_1728_2_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.gb.tets.Test

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO создать проект в гит
        // TODO cherry-Pick ????
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
        Repository.getData()

        findViewById<TextView>(R.id.tv).setOnClickListener(object:View.OnClickListener {
            override fun onClick(v: View?) {

            }
        })

        val callback = object:View.OnClickListener {
            override fun onClick(v: View?) {

            }
        }

        val cat = object {
            val name = "Kisa"
            val age = 3
        }

        Log.d("mylogs", "${cat.name} ${cat.age}")

        Person.test
        Person.testFun()
        Person.testPublicStaticFinal

    }
}
