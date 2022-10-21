package at.technikum_wien.if20b049.weatherApp.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem

@Composable
fun DetailScreen(navController: NavController, weatherItem : WeatherItem?) {
    val scrollState = rememberScrollState()

    Column {
        TopAppBar(
            title = {
                Text(text = "Weather Details")
            },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Text(text = "Forecast for ")
            Text(text = weatherItem?.dt.toString() ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Icon ")
            Text(text = weatherItem?.icon ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Temperature: ")
            Text(text = weatherItem?.temp ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Pressure: ")
            Text(text = weatherItem?.pres ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Humidity: ")
            Text(text = weatherItem?.hum ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Pressure: ")
            Text(text = weatherItem?.pres ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Cloud cover: ")
            Text(text = weatherItem?.cloud ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Wind speed: ")
            Text(text = weatherItem?.speed ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Direction: ")
            Text(text = weatherItem?.deg ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Rain (last 3h):  ")
            Text(text = weatherItem?.rain ?: "")
        }
        Row(
            modifier = Modifier
                .verticalScroll(scrollState)
        ){
            Text(text = "Snow (last 3h): ")
            Text(text = weatherItem?.snow ?: "")
        }
    }
}
