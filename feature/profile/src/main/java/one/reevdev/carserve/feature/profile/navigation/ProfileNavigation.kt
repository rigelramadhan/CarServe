package one.reevdev.carserve.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.feature.profile.screen.input.InputCustomerRouter
import one.reevdev.carserve.feature.profile.screen.list.CustomerListRouter
import one.reevdev.carserve.feature.profile.screen.serviceadvisor.ServiceAdvisorRouter

fun NavController.navigateToInputCustomer() {
    navigate(ProfileRoutes.InputCustomer)
}

fun NavGraphBuilder.inputCustomerScreen(
    onSubmit: (param: Customer) -> Unit,
) {
    composable<ProfileRoutes.InputCustomer> {
        InputCustomerRouter(
            onSubmit = onSubmit,
        )
    }
}

fun NavController.navigateToServiceAdvisor() {
    navigate(ProfileRoutes.ServiceAdvisor)
}

fun NavGraphBuilder.serviceAdvisorScreen() {
    composable<ProfileRoutes.ServiceAdvisor> {
        ServiceAdvisorRouter()
    }
}

fun NavController.navigateToCustomerList() {
    navigate(ProfileRoutes.CustomerList)
}

fun NavGraphBuilder.customerListScreen(
    onCustomerClick: (String) -> Unit,
) {
    composable<ProfileRoutes.CustomerList> {
        CustomerListRouter(
            onCustomerClick = onCustomerClick,
        )
    }
}