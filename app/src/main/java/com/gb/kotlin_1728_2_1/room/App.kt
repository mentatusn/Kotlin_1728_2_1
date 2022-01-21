package com.gb.kotlin_1728_2_1.room

import android.app.Application
import androidx.room.Room
import java.util.*

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance:App? =null
        private const val DB_NAME = "History.db"
        private var db:HistoryDatabase?=null

        fun getHistoryWeatherDao():HistoryWeatherDao{
            if(db==null){
                if(appInstance==null){ throw  IllformedLocaleException("Все очень плохо") }
                else{
                    db = Room.databaseBuilder(appInstance!!.applicationContext,HistoryDatabase::class.java,DB_NAME)
                        //.allowMainThreadQueries() // TODO нужно убрать эту строку
                        .build()
                }
            }
            return db!!.historyWeatherDao()
        }
    }

}