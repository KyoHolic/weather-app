package at.technikum_wien.if20b049.weatherApp.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem
import at.technikum_wien.if20b049.weatherApp.data.download.WeatherDownloader
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class WeatherListViewModel() : ViewModel() {
    private val _weatherItems = MutableLiveData<List<WeatherItem>>(listOf())
    private val _error = MutableLiveData<Boolean>(false)
    private val _busy = MutableLiveData<Boolean>(true)

    init {
        reload()
    }

    val weatherItems : LiveData<List<WeatherItem>>
        get() = _weatherItems
    val error : LiveData<Boolean>
        get() = _error
    val busy : LiveData<Boolean>
        get() = _busy

    @RequiresApi(Build.VERSION_CODES.O)
    private fun downloadWeatherItems(weatherFeedUrl: String) {
        _error.value = false
        _weatherItems.value = listOf()
        _busy.value = true
        viewModelScope.launch {
            val value = WeatherDownloader().loadWebResult(weatherFeedUrl)
            if (value == null)
                _error.value = true
            else
              _weatherItems.value = value
            _busy.value = false
        }
    }

    fun reload() {
        downloadWeatherItems("https://api.openweathermap.org/data/2.5/forecast?q=Vienna,AT&appid=7776c10840f221b6c1ffb654afbaacc3")
    }
}