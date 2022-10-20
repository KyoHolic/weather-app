package at.technikum_wien.if20b049.step1.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import at.technikum_wien.if20b049.step1.data.WeatherItem
import at.technikum_wien.if20b049.step1.ui.theme.Step1Theme

class DetailActivity : ComponentActivity() {
    /*

    private val weatherItem: WeatherItem by lazy {
        intent?.getSerializableExtra(WEATHER_ID) as WeatherItem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wicon = weatherItem.icon
        val wcond = weatherItem.condition
        val wdatetime = weatherItem.date_time
        val wtemp = weatherItem.temp
        val wpres = weatherItem.pressure
        val whum = weatherItem.humidity
        val wclou = weatherItem.cloud
        val wwinsp = weatherItem.wind_speed
        val wwindi = weatherItem.wind_direct
        val wrain = weatherItem.rain
        val wsnow = weatherItem.snow

        setContent{
            Step1Theme{
                Column() {
                    Text(text = "Icon: $wicon")
                    Text(text = "Condition: $wcond")
                    Text(text = "Date and Time: $wdatetime")
                    Text(text = "Temperature: $wtemp")
                    Text(text = "Pressure: $wpres")
                    Text(text = "Humidity: $whum")
                    Text(text = "Cloud cover: $wclou")
                    Text(text = "Wind speed: $wwinsp")
                    Text(text = "Wind direction: $wwindi")
                    Text(text = "Rain (last 3h): $wrain")
                    Text(text = "Snow (last 3h): $wsnow")
                }
            }
        }
    }

    companion object{
        private const val WEATHER_ID = "weather_id"
        fun newIntent(context: Context, weatherItem: WeatherItem) =
            Intent(context, DetailActivity::class.java).apply {
                putExtra(WEATHER_ID, weatherItem)
            }
    }*/
}