package one.reevdev.carserve.feature.service.navigation.routes

import one.reevdev.carserve.feature.service.navigation.RouteConstants

sealed class AnalysisRoutes(val route: String) {
    data object Camera : AnalysisRoutes(RouteConstants.CAMERA)
    data object Form : AnalysisRoutes(RouteConstants.FORM)
    data object Analysis : AnalysisRoutes(RouteConstants.ANALYSIS)
}