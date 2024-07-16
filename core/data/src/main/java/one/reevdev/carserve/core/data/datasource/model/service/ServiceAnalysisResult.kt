package one.reevdev.carserve.core.data.datasource.model.service

import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleData

data class ServiceAnalysisResult(
    val vehicle: VehicleData,
    val recommendedAction: String,
    val findings: List<Finding>,
    val analysisHtml: String
)

data class Finding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
