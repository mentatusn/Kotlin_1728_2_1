package com.gb.kotlin_1728_2_1.lesson4

import android.util.Log

class LambdaKotlin {


    data class Person(var name: String, var age:Int)

    fun main() {

        var p:Person? = null



        if(p!=null){

            Log.d("mylogs","${p.name} ${p.age}")
        }

        p?.let {
            p=null
            Log.d("mylogs","${it.name} ${it.age}")
        }
        p?.run {
            Log.d("mylogs","${this.name} ${age}")
        }
        val person2 = Person("",1)
        val person = Person("",1)



        //with()
        val resultWith = with(person){
            this.name = "sdf"
            age = 1
            2
        }

        //.let
        val newPerson2 = person.let {
            ""
        }
        //.run
        run { // отдельная область видимости
            val person2 = Person("",1)
        }

        val resultRun = person.run {
            this.name = "ApplyName"
            5f
        }

        //.also
        val newPersonAlso = person.also {
            it.name = "ApplyName"
            it.name = "ApplyName"
            it.age = 50
        }
        //.apply
        val newPersonApply= person.apply {
            this.name = "ApplyName"
            name = "ApplyName"
            age = 50
        }

    }



}
