package at.technikum_wien.if20b049.weatherApp

import androidx.compose.runtime.Composable
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem

@Composable
fun WeatherHomeContent(navigateToDetail: (WeatherItem) -> Unit){
    /*
    val weatherItems = remember { DataProvider.weatherItemLists}
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ){
        items(
            items = weatherItems,
            itemContent = {
                WeatherListItem(weatherItem = it, navigateToDetail)
            }
        )
    }*/
}