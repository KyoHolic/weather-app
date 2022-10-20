package at.technikum_wien.if20b049.step1.data.parser

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import at.technikum_wien.if20b049.step1.data.WeatherItem
import at.technikum_wien.if20b049.step1.view.MainActivity
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
    fun parseResponse(response : String) : List<String> {
        val jsonRoot = JSONObject(response)
        val list = jsonRoot.optJSONArray("list")
        val stationSet = mutableSetOf<String>()

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

                val main = entry?.optJSONObject("main")
                val temp = main?.optString("temp")
                val temp2 = (temp!!.toFloat() - 273.15)
                val tempFormat = String.format("%.2f °C\n", temp2)

                if (temp != null && dt != null)  {

                    stationSet.add(output)
                    if (icon != null) {
                        stationSet.add(icon)
                    }
                    stationSet.add(tempFormat)
                }
            }
        }
        return stationSet.toList()
    }

}