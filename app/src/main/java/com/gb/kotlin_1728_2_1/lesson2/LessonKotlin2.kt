package com.gb.kotlin_1728_2_1.lesson2

import android.util.Log
import android.widget.Button
import java.util.*

object LessonKotlin2 : Any() {


    fun main(): Unit {
        val phrase: Array<String> = arrayOf("I", "love", "Kotlin")
        val lang = phrase[2]
        phrase[1] = "know"
        val wordCount = phrase.size

        class Person(val name: String, var age: Int)

        val peopleUn: List<Person> = listOf(Person("Петя", 20), Person("Оля", 22))
        peopleUn.get(0)// Петя
        peopleUn.get(1)// Оля
        peopleUn[0]// Петя
        peopleUn[1]// Оля
        peopleUn[0].age = 21
        val peopleMutable: MutableList<Person> = mutableListOf(Person("Петя", 20), Person("Оля", 22))
        peopleMutable.add( Person("Оля", 22))
        val peopleMutable2: MutableList<Person> = peopleUn.toMutableList()

        // setOf() -> mulablesetOf()
        // mapOf() ->mutablemapOf()

        val listFOrExt: List<Int> = listOf(1,3,6,2,4)

        Log.d("mylogs","${listFOrExt.filter { it>3 }}")
        Log.d("mylogs","${listFOrExt.sorted()}}")
        Log.d("mylogs","${listFOrExt.last()} $wordCount last")
        Log.d("mylogs", String.format("${listFOrExt.last()}"," ","last"))
    }


}