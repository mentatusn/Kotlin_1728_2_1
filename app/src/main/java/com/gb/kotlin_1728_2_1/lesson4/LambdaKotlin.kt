package com.gb.kotlin_1728_2_1.lesson4

import android.util.Log

class LambdaKotlin {

    fun main() {
        val l = {
            Log.d("mylogs", " run1 ")
            1 // одна точка выхода
        }
        val l2 = blabla@{
            Log.d("mylogs", " run1 ")
            if (false)
                return@blabla 2 // первая точка выхода
            else
                return@blabla 3 // вторая точка выхода
        }

        Log.d("mylogs", "${l()} run2")
    }

    fun foo(): Int {
        Log.d("mylogs", " run1 ")
        if (false)
            return 2
        else
            return 3
    }
}