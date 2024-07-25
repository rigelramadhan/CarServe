package one.reevdev.carserve.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.feature.profile.screen.input.InputProfileRouter

fun NavController.navigateToInputProfile() {
    navigate(ProfileRoutes.InputProfile)
}

fun NavGraphBuilder.inputProfileScreen(
    onSubmit: (param: SavedProfile) -> Unit,
) {
    composable<ProfileRoutes.InputProfile> {
        InputProfileRouter(
            onSubmit = onSubmit,
        )
    }
}