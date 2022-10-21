package at.technikum_wien.if20b049.weatherApp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import at.technikum_wien.if20b049.weatherApp.viewmodels.WeatherListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController, viewModel : WeatherListViewModel) {
    val weatherItems by viewModel.weatherItems.observeAsState()
    val error by viewModel.error.observeAsState()
    val busy by viewModel.busy.observeAsState()

    Column {
        TopAppBar(title = {
            Text(text = "Weather Home")
        })
        if (error == true)
            Text(text = "An error occurred. Please try again.")
        Button(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.reload()
            },
            enabled = !(busy ?: false)
        ) {
            Text(text = "Reload", fontSize = 20.sp)
        }
        LazyColumn(Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ){
            itemsIndexed(weatherItems ?: listOf()) { index, weatherItem ->
                WeatherItemRow(navController = navController, index = index, weatherItem = weatherItem)
            }
        }
    }
}
