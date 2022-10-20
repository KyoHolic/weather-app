package at.technikum_wien.if20b049.step1.view

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.platform.LocalContext
import at.technikum_wien.if20b049.step1.WeatherHomeContent
import at.technikum_wien.if20b049.step1.data.WeatherItem
import at.technikum_wien.if20b049.step1.ui.theme.Step1Theme
import at.technikum_wien.if20b049.step1.viewmodels.WeatherListViewModel
import org.json.JSONObject


class MainActivity : ComponentActivity() {
    companion object {
        val LOG_TAG = MainActivity::class.simpleName
        const val RESULTS_KEY = "results"
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /*Step1Theme {
                MyApp{
                    startActivity(DetailActivity.newIntent(this, it))
                }
            }*/
            ListScreen(WeatherListViewModel())
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ListScreen(viewModel : WeatherListViewModel) {
    val context = LocalContext.current
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
                        Text(weatherItem.date_time)
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



