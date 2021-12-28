package com.gb.kotlin_1728_2_1.lesson4

import android.util.Log


interface Base1{
    fun someFun1(print:String):Int
}
interface Base2{
    fun someFun2()
}

class BaseImpl1():Base1{
    override fun someFun1(print:String):Int {
        return Log.d("mylogs"," someFun1 $print")
    }
}
class BaseImpl2():Base2{
    override fun someFun2() {
        Log.d("mylogs"," someFun2")
    }
}

class LazyImpl(base1:Base1,base2:Base2):Base1 by base1,Base2 by base2{

}

fun main(){
    val base1 = BaseImpl1()
    val base2 = BaseImpl2()
    val boss = LazyImpl(base1,base2)

    Log.d("mylogs"," ${    boss.someFun1("boss")}")
    boss.someFun2()
}