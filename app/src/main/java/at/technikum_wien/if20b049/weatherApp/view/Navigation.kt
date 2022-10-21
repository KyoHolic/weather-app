package at.technikum_wien.if20b049.weatherApp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import at.technikum_wien.if20b049.weatherApp.data.WeatherItem
import at.technikum_wien.if20b049.weatherApp.ui.theme.Step1Theme
import at.technikum_wien.if20b049.weatherApp.viewmodels.WeatherListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(viewModel : WeatherListViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.DetailScreen.route + "/{newsItemIndex}",
            arguments = listOf(
                navArgument(name = "newsItemIndex") {
                    type = NavType.IntType
                }
            )
        ) {
            var item : WeatherItem? = null
            val index = it.arguments?.getInt("weatherItemIndex")
            if (index != null && (viewModel.weatherItems.value?.size ?:0) > index)
                item = viewModel.weatherItems.value?.get(index)
            DetailScreen(navController = navController, weatherItem = item)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Step1Theme{
        Navigation(WeatherListViewModel())
    }
}