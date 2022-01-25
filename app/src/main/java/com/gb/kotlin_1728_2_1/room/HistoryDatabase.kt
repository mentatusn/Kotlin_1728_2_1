package com.gb.kotlin_1728_2_1.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database( entities = [HistoryWeatherEntity::class], version = 4, exportSchema = false)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyWeatherDao():HistoryWeatherDao
}