package at.technikum_wien.if20b049.weatherApp.view

sealed class Screen (val route : String) {
    object MainScreen : Screen(route = "main_screen")
    object DetailScreen : Screen(route = "detail_screen")
}
