package com.gb.kotlin_1728_2_1.lesson2

import android.util.Log
import android.widget.Button
import java.util.*

class Car(val name:String,val age:Int) : Any(),FlyingVehicle {

    fun main(): Unit {

    }

    override val maxHeight: Float
        get() = 6000f
    override val maxSpeed: Float
        get() = 300f

    override fun speed(weight: Float): Float {
        return maxSpeed
    }
}