package com.gb.kotlin_1728_2_1.lesson2

import android.util.Log

class Car(val name:String,val age:Int) : Any(),FlyingVehicle {



    override val maxHeight: Float
        get() = 6000f
    override val maxSpeed: Float
        get() = 300f

    override fun speed(weight: Float): Float {


        return maxSpeed
    }



    fun writeInt(input:Int) = Log.d("mylogs",input.toString())
    fun writeString(input:String) = Log.d("mylogs",input.toString())
    fun writeDouble(input:Double) = Log.d("mylogs",input.toString())


    fun <T:Any> write(input:T) = Log.d("mylogs",input.toString())

    fun main(): Unit {
        writeInt(1)
        writeDouble(1.0)
        writeString("1")

        write(1)
        write(1.0)
        write("1")

        val producer:Producer<Any> = Producer<String>()
        producer.produce()
    }
}

fun <T:CharSequence> T.myToString():String{
    return "бип бип"
}

fun Any.myToString():String{
    return "бип бип"
}

class Producer<out T>{
    private val hack = mutableListOf<T>()
    fun produce():T{
        return hack.last()
    }
}

class Consumer<in T>{
    fun consume(param:T){

    }
}