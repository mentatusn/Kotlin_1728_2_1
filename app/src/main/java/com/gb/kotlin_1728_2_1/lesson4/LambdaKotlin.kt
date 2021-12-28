package com.gb.kotlin_1728_2_1.lesson4

import android.util.Log

class LambdaKotlin {

    private val field1 = 1
    protected val field2 = 1
    fun main() {
        val str = "word word"
        val charCount1 = str.length
        val charCount2 = str.charCount('w')
        Log.d("mylogs","$charCount2")
    }

    //fun String.charCount

    fun String.charCount(char: Char):Int{
        var counter = 0
        for (ch in this){
            if(ch==char) counter++
        }
        return counter
    }
    fun Any.compareTo():Int{
        return -1
    }


}

fun LambdaKotlin.getF1():Int{
    return 1//this.field1 не имеем доступа к private
}
fun LambdaKotlin.getF2():Int{
    return 2//this.field1 не имеем доступа к protected
}