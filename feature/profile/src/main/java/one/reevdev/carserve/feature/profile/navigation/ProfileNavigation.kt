package one.reevdev.carserve.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.feature.profile.screen.customer.InputCustomerRouter

fun NavController.navigateToInputCustomer() {
    navigate(ProfileRoutes.InputCustomer)
}

fun NavGraphBuilder.inputCustomerScreen(
    onSubmit: (param: SavedProfile) -> Unit,
) {
    composable<ProfileRoutes.InputCustomer> {
        InputCustomerRouter(
            onSubmit = onSubmit,
        )
    }
}