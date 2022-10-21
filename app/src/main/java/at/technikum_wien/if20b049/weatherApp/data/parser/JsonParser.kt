package at.technikum_wien.if20b049.weatherApp.data.parser

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem
import at.technikum_wien.if20b049.weatherApp.view.MainActivity
import org.json.JSONObject
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class JsonParser {
    companion object{
        val LOG_TAG: String = JsonParser::class.java.canonicalName ?: "JsonParser"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun parseResponse(response : String) : List<WeatherItem> {
        val jsonRoot = JSONObject(response)
        val list = jsonRoot.optJSONArray("list")
        val stationSet = mutableSetOf<WeatherItem>()

        if (list == null)
            Log.w(MainActivity.LOG_TAG, "No lists found.")
        else {
            for (i in 0 until list.length()) {
                val entry = list.optJSONObject(i)
                val dt = entry.optString("dt").toLong()

                val dtFormat = Instant.ofEpochSecond(dt)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
                    .toString()

                val localDateTime = LocalDateTime.parse(dtFormat);
                val formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm:ss");
                val output = formatter.format(localDateTime).toString()

                val weather = entry?.optJSONArray("weather")
                val zero = weather?.optJSONObject(0)
                val icon = zero?.optString("icon")
                val id = zero?.optInt("id")

                val main = entry?.optJSONObject("main")
                val temp = main?.optString("temp")
                val temp2 = (temp!!.toFloat() - 273.15)
                val tempFormat = String.format("%.2f °C\n", temp2)

                val pres = main?.optString("pressure")
                val presFormat = String.format("%.2f hPa\n", pres.toFloat())

                val hum = main.optString("humidity")
                val humFormat = String.format("%.2f hPa\n", hum.toFloat())

                val clouds = entry?.optJSONObject("clouds")
                val clo = clouds.optString("all")
                val cloFormat = String.format("%.2f hPa\n", clo.toFloat())

                val wind = entry?.optJSONObject("wind")
                val speed = wind?.optString("speed")
                val speedFormat = speed?.let { String.format("%.2f kph\n", it.toFloat()) }

                val deg = wind?.optString("deg")
                val degFormat = deg?.let { String.format("%.2f deg\n", it.toFloat()) }

                val rain = entry?.optJSONObject("rain")
                var three = rain?.optString("3h")
                if (three == null){
                    three = "0.00"
                }
                val rainFormat = three?.let { String.format("%.2f mm\n", it.toFloat()) }

                val snow = entry?.optJSONObject("snow")
                var three2 = snow?.optString("3h")
                if (three2 == null){
                    three2 = "0.00"
                }
                val snowFormat = three2?.let { String.format("%.2f mm\n", it.toFloat()) }

                if (temp != null && dt != null && id != null && icon != null && presFormat != null && humFormat != null && cloFormat != null && speedFormat != null && degFormat != null && rainFormat != null && snowFormat != null  )  {

                    stationSet.add(WeatherItem(id, output , icon, tempFormat, presFormat, humFormat, cloFormat, speedFormat, degFormat, rainFormat, snowFormat ))
                }
            }
        }
        return stationSet.toList()
    }

}