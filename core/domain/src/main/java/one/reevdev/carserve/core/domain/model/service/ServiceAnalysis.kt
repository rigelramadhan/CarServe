package one.reevdev.carserve.core.domain.model.service

import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.model.vehicle.Vehicle

data class ServiceAnalysis(
    val vehicle: Vehicle? = null,
    val recommendedAction: String = emptyString(),
    val serviceFindings: List<ServiceFinding> = emptyList(),
    val totalEstimatedPrice: Double = 0.0,
)

data class ServiceFinding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
