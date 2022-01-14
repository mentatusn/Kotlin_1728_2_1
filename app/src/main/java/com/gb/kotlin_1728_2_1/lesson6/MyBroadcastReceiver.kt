package com.gb.kotlin_1728_2_1.lesson6

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class MyBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("mylogs2", "onReceive() ${intent?.action} ${intent?.getStringExtra(MAIN_SERVICE_KEY_EXTRAS)}")
    }
}