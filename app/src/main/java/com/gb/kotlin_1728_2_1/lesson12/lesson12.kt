package com.gb.kotlin_1728_2_1.lesson12


fun main() {
    val s1 = Soldier()
    s1.instrument = Shovels.ShovelWide()
    (s1.instrument  as  Shovels.ShovelWide).excavate1()
    s1.instrument = Shovels.ShovelWide()
    (s1.instrument  as  Shovels.ShovelThin).excavate2()
    //s1.instrument.
}

class Soldier() {
    lateinit var instrument: Shovel
}

sealed class Shovels {
    class ShovelWide() : Shovel("wide") {
        fun excavate1() {
            // кто-то копает 1 способом
        }
    }

    class ShovelThin() : Shovel("thin") {
        fun excavate2() {
            // кто-то копает 2 способом
        }
    }
}

abstract class Shovel(val name: String) {
    // excavate()
}