package com.gb.kotlin_1728_2_1.lesson2

import android.util.Log

class LessonKotlin2 {


    lateinit var nameNotNullable:String // гарантирую что присвою

    var nameNullable:String? = null // а вот здесь нам напомнит компилятор


    fun main(){
        val strLen1 = nameNotNullable.length

        val strLen2 = nameNullable?.length // safe call
    }
}