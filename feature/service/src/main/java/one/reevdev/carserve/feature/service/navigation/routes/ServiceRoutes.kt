package one.reevdev.carserve.feature.service.navigation.routes

import one.reevdev.carserve.feature.service.navigation.RouteConstants

sealed class ServiceRoutes(val route: String) {
    data object Service : ServiceRoutes(route = "${RouteConstants.SERVICE}/{${RouteConstants.ARGUMENT_INIT_VEHICLE}}") {
        fun getRoute(initVehicleJson: String? = null): String {
            return "${RouteConstants.SERVICE}/${initVehicleJson.orEmpty()}"
        }
    }
}