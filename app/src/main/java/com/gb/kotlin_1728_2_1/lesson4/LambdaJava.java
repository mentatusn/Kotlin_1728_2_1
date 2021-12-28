package com.gb.kotlin_1728_2_1.lesson4;

import android.util.Log;

public class LambdaJava {
    public void main(){
        Operation op = (x, y) -> x+y;
        {
            int z = op.calculate(1,2);
        }
        int z = op.calculate(1,2);
        Log.d("mylogs",z+"");

    }
}
interface Operation{
    int calculate(int x, int y);
}
