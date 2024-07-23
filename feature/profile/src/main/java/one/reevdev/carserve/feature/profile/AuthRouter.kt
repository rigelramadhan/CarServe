package one.reevdev.carserve.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import one.reevdev.carserve.feature.profile.navigation.AuthRoutes
import one.reevdev.carserve.feature.profile.navigation.loginScreen

@Composable
fun AuthRouter(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: AuthRoutes.Login,
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