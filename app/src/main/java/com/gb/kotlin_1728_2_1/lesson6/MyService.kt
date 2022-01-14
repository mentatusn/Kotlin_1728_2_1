package com.gb.kotlin_1728_2_1.lesson6

import android.app.IntentService
import android.content.Intent
import android.util.Log


const val MAIN_SERVICE_KEY_EXTRAS = "key_"

class MyService(name:String=""): IntentService(name) {

    private val TAG = "mylogs"

    private fun createLogMessage(message: String) {
        Log.d(TAG, message)
    }

    override fun onHandleIntent(intent: Intent?) {
        createLogMessage("onHandleIntent ${intent?.getStringExtra(MAIN_SERVICE_KEY_EXTRAS)}")
    }

    override fun onCreate() {
        super.onCreate()
        createLogMessage("unCreate ")
    }

    override fun onDestroy() {
        super.onDestroy()
        createLogMessage("onDestroy ")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createLogMessage("onStartCommand ${flags} ")
        //  по умолчанию флаг IntentService.START_STICKY_COMPATIBILITY
        return super.onStartCommand(intent, flags, startId)
    }

}