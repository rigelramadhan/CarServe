package one.reevdev.carserve.feature.service.navigation

sealed class ServiceRoutes {
    data object Service : AnalysisRoutes(RouteConstants.SERVICE)
}