package one.reevdev.carserve.core.domain.model.service

import one.reevdev.carserve.core.common.data.emptyString

data class ServiceAnalysis(
    val id: Int = 0,
    val vehicleId: Int = 0,
    val recommendedAction: String = emptyString(),
    val serviceFindings: List<ServiceFinding> = emptyList(),
    val totalEstimatedPrice: Double = 0.0,
)

data class ServiceFinding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
