package com.gb.kotlin_1728_2_1.lesson6

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context:Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        Log.d("mylogs", "doWork()")
        return Result.success()
    }
}