package one.reevdev.carserve.feature.service.navigation.routes

import one.reevdev.carserve.feature.service.navigation.RouteConstants

sealed class ServiceRoutes {
    data object Service : AnalysisRoutes(RouteConstants.SERVICE)
}