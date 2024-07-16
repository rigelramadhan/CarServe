package one.reevdev.carserve.core.domain.feature.service.model

import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

data class ServiceAnalysis(
    val vehicle: Vehicle? = null,
    val recommendedAction: String = emptyString(),
    val serviceFindings: List<ServiceFinding> = emptyList(),
    val totalEstimatedPrice: Double = 0.0,
    val analysisHtml: String = emptyString(),
)

data class ServiceFinding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
