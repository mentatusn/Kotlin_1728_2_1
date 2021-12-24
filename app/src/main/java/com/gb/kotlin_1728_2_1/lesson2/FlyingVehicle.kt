package com.gb.kotlin_1728_2_1.lesson2

interface FlyingVehicle {
    val maxHeight: Float
    val maxSpeed: Float

    val greeting: String
        get() = "Бип бип"

    fun speed(weight: Float): Float
}

interface Military {

}