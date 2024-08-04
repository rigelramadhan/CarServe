package one.reevdev.carserve.core.data.feature.service.datasource.model

import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity

data class ServiceAnalysisResult(
    val profile: CustomerEntity,
    val vehicle: CustomerVehicleEntity,
    val recommendedAction: String,
    val findings: List<Finding>,
    val analysisHtml: String,
    val createDate: String,
)

data class Finding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
