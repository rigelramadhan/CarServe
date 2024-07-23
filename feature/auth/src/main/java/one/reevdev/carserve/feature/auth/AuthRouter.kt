package one.reevdev.carserve.feature.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.auth.navigation.AuthRoutes
import one.reevdev.carserve.feature.auth.navigation.loginScreen

@Composable
fun AuthRouter(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Any = AuthRoutes.Login,
    navigateToHome: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        loginScreen(
            navigateToRegister = {},
            navigateToHome = navigateToHome
        )
    }
}