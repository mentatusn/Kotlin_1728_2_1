package com.gb.kotlin_1728_2_1.lesson2

import android.util.Log
import android.widget.Button
import java.util.*

class LessonKotlin2 : Any() {


    lateinit var nameNotNullable: String // гарантирую что присвою

    var nameNullable: String? = null // а вот здесь нам напомнит компилятор


    fun main(): Unit {

        val o = Object()
        o.toString()
        val i: Int = 1
        val d: Double =
            i.toString().toInt().toDouble().toInt().toDouble().toInt().toDouble().toInt().toDouble()
                .toInt().toDouble()

        val strLen1 = nameNotNullable.length
        val strLen2 = nameNullable?.length // safe call
        //nameNullable  ="sdgsrg"
        val strLen3 = (nameNullable ?: "").length // оператор Элвиса
        main2(strLen3)

        val anyAny: Any = Any()
        val testObject: Object = Object()
        //Unit != void
    }

    fun main2(i: Int):Unit {

    }
}