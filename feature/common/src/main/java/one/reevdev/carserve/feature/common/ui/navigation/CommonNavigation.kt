package one.reevdev.carserve.feature.common.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import one.reevdev.carserve.feature.common.ui.screen.SuccessRouter

fun NavController.navigateToSuccessScreen(text: String) {
    navigate(CommonRoutes.Success(text))
}

fun NavGraphBuilder.successScreen(
    onProceed: () -> Unit,
) {
    composable<CommonRoutes.Success> {
        val text = it.toRoute<CommonRoutes.Success>().text
        SuccessRouter(value = text, onProceed = onProceed)
    }
}