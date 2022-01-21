package com.gb.kotlin_1728_2_1.room

import android.app.Application
import androidx.room.Room
import java.util.*

// нигде не используется, почему? Потому что его нет в  <application> AndroidManifest.xml
class AppSecond: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance:AppSecond? =null
        const val DB_NAME = "History.db"
        private var db:HistoryDatabase?=null

        fun getHistoryWeatherDao():HistoryWeatherDao{
            if(db==null){
                if(appInstance==null){ throw  IllformedLocaleException("Все очень плохо") }
                else{
                    db = Room.databaseBuilder(appInstance!!.applicationContext,HistoryDatabase::class.java,DB_NAME)
                        .allowMainThreadQueries() // TODO нужно убрать эту строку
                        .build()
                }
            }
            return db!!.historyWeatherDao()
        }
    }

}