package at.technikum_wien.if20b049.step1.data

import java.io.Serializable

data class WeatherItem(
    val id: Int,
    val dt: String,
    val icon: String,
    val date_time: String,
    val temp: String
) : Serializable
