package at.technikum_wien.if20b049.weatherApp.data

import java.io.Serializable

data class WeatherItem(
    val id: Int,
    val dt: String,
    val icon: String,
    val temp: String,
    val pres: String,
    val hum: String,
    val cloud: String,
    val speed: String,
    val deg: String,
    val rain: String,
    val snow: String
) : Serializable
