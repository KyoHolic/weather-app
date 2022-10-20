package at.technikum_wien.if20b049.step1

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import at.technikum_wien.if20b049.step1.data.WeatherItem

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