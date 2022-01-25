package com.gb.kotlin_1728_2_1.room

import android.database.Cursor
import androidx.room.*

@Dao
interface HistoryWeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: HistoryWeatherEntity)

    @Delete
    fun delete(entity: HistoryWeatherEntity)

    @Update
    fun update(entity: HistoryWeatherEntity)

    @Query("select * FROM history_weather_entity")
    fun getAllHistoryWeather():List<HistoryWeatherEntity>

    //fun getAllHistoryWeather() // TODO по какому-то полю получить, было бы неплохо

    /** LESSON 9**/
    @Query("DELETE FROM history_weather_entity WHERE id=:id")
    fun delete(id: Long)

    @Query("SELECT * FROM history_weather_entity WHERE id=:id")
    fun getHistoryCursor(id: Long):Cursor
    /****/
}