package at.technikum_wien.if20b049.weatherApp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem

@Composable
fun WeatherListItem(weatherItem: WeatherItem, navigateToDetail: (WeatherItem) -> Unit){
    Row(
        Modifier.clickable { navigateToDetail(weatherItem)}
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = weatherItem.dt)
            Text(text = weatherItem.icon)
            Text(text = weatherItem.temp)
        }
    }
}