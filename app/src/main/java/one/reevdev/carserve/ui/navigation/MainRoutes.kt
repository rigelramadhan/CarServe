package one.reevdev.carserve.ui.navigation

sealed class MainRoutes(val route: String) {
    data object Home : MainRoutes(MainConstants.HOME)
}