package com.gb.kotlin_1728_2_1.lesson2

import android.util.Log
import android.widget.Button

class LessonKotlin2 {


    lateinit var nameNotNullable:String // гарантирую что присвою

    var nameNullable:String? = null // а вот здесь нам напомнит компилятор


    fun main(){
        val strLen1 = nameNotNullable.length
        val strLen2 = nameNullable?.length // safe call
        //nameNullable  ="sdgsrg"
        val strLen3 = (nameNullable?:"").length // оператор Элвиса
        main2(strLen3)


    }

    fun main2(i:Int){

    }
}