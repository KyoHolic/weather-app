package at.technikum_wien.if20b049.weatherApp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import at.technikum_wien.if20b049.weatherApp.WeatherHomeContent
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem
import at.technikum_wien.if20b049.weatherApp.ui.theme.Step1Theme
import at.technikum_wien.if20b049.weatherApp.view.Navigation
import at.technikum_wien.if20b049.weatherApp.viewmodels.WeatherListViewModel
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MainActivity : ComponentActivity() {
    companion object {
        val LOG_TAG = MainActivity::class.simpleName
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<WeatherListViewModel>()

        setContent {
            Step1Theme {
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(viewModel = viewModel)
                }
            }
            // ListScreen(WeatherListViewModel())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListScreen(viewModel : WeatherListViewModel) {
    val weatherItems by viewModel.weatherItems.observeAsState()
    val busy by viewModel.busy.observeAsState()

    Step1Theme() {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                TopAppBar(title = { Text("Weather App") })
                Button(onClick = {
                    viewModel.reload()
                },
                enabled = !(busy ?: false)){
                    Text("Generate")
                }
                LazyColumn(Modifier.fillMaxWidth()) {
                    itemsIndexed(weatherItems ?: listOf()) { index, weatherItem ->
                        Text(weatherItem.dt)
                    }
                }
            }
        }
    }
}

@Composable
fun MyApp(navigateToDetail: (WeatherItem) -> Unit){

    Scaffold {
        WeatherHomeContent(navigateToDetail = navigateToDetail)

    }
}



/*override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    val resultsString = resultTextView?.text?.toString() ?: ""
    outState.putString(RESULTS_KEY, resultsString)
}

override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    val resultsString = savedInstanceState.getString(RESULTS_KEY)
    resultTextView?.text = resultsString
}*/



