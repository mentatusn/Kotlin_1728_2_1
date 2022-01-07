package com.gb.kotlin_1728_2_1.lesson4

import android.util.Log
import android.view.View

class LambdaKotlin {

    fun interface OnMyListener {
        fun onSomething(string: String?)
    }

    var mOnMyListener: OnMyListener? = null
    fun setOnMyListener(l: OnMyListener?) {
        mOnMyListener = l
    }

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
        //Log.d("mylogs", "${l()} run2")

        val textLambda = lambdaFun(1)
        val textAnonim = anonimFun(1)

        anotherFun(lambdaFun,anonimFun)


        val l3 ={param1:String,param2:Int,param3:Float ->
            2+2
        }
        val result = l3("",1,1f)

        mOnMyListener?.onSomething("что-то произошло")

    }

    fun anotherFun(block1:(i: Int)->String,block2:(i: Int)->String){
        Log.d("mylogs", " anotherFun ")
        Log.d("mylogs", " ${block1(2)} ")
        Log.d("mylogs", " ${lambdaFun(2)} ")
        Log.d("mylogs", " ${block2(2)} ")
        Log.d("mylogs", " ${anonimFun(2)} ")
    }

    val anonimFun = fun(inputInteger: Int): String {
        Log.d("mylogs", "anonimFun $inputInteger ")
        return "anonimFun" // одна точка выхода
    }

    val lambdaFun = { i: Int ->
        Log.d("mylogs", "lambdaFun $i ")
        "lambdaFun" // одна точка выхода
    }

    fun foo(): Int {
        Log.d("mylogs", " run1 ")
        if (false)
            return 2
        else
            return 3
    }
}