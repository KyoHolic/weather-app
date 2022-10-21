package at.technikum_wien.if20b049.weatherApp.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.navigation.NavController
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun WeatherItemRow (navController : NavController, index : Int, weatherItem: WeatherItem) {
    ClickableText(
        text = AnnotatedString(text = weatherItem.dt),
        onClick = {
            navController.navigate(Screen.DetailScreen.route + "/${index}")
        }
    )

    Text(text = weatherItem.icon)
    GlideImage(imageModel = "http://openweathermap.org/img/wn/10d@2x.png",
        contentScale = ContentScale.Fit,
        circularReveal = CircularReveal(duration = 250),
        modifier = Modifier.size(80.dp),
        placeHolder = Icons.Filled.Image)


    Text(text = weatherItem.temp)

}

