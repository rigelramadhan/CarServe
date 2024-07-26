package one.reevdev.carserve.core.data.feature.service.datasource.model

import one.reevdev.carserve.core.data.feature.profile.datasource.model.local.LastSavedProfile
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity

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
