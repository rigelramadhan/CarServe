package one.reevdev.carserve.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.feature.auth.AuthRouter
import one.reevdev.carserve.feature.auth.screen.login.LoginRouter

fun NavController.navigateToLogin() {
    navigate(AuthRoutes.Login)
}

fun NavGraphBuilder.loginScreen(navigateToRegister: () -> Unit, navigateToHome: () -> Unit) {
    composable<AuthRoutes.Login> {
        LoginRouter(navigateToRegister = navigateToRegister, navigateToHome = navigateToHome)
    }
}

fun NavGraphBuilder.authRouter(navigateToHome: () -> Unit) {
    composable<AuthRoutes.Login> {
        AuthRouter(navigateToHome = navigateToHome)
    }
}