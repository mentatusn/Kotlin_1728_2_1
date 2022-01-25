package com.gb.kotlin_1728_2_1.room

import androidx.room.Entity
import androidx.room.PrimaryKey

const val ID = "id"
const val NAME = "name"
const val TEMPERATURE = "temperature"
const val FEELS_LIKE = "feelsLike"
const val ICON = "icon"


@Entity(tableName = "history_weather_entity")
data class HistoryWeatherEntity(
    @PrimaryKey(autoGenerate = true) var id: Long=0,
    var name: String="",
    var temperature: Int=0,
    var feelsLike: Int=0,
    var icon4: String=""
)
