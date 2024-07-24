package one.reevdev.carserve.core.data.datasource.model.service

import one.reevdev.carserve.core.data.datasource.model.profile.LastSavedProfile
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity

data class ServiceAnalysisResult(
    val profile: LastSavedProfile,
    val vehicle: VehicleEntity,
    val recommendedAction: String,
    val findings: List<Finding>,
    val analysisHtml: String
)

data class Finding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
