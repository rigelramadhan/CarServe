package one.reevdev.carserve.core.domain.model.service

data class ServiceAnalysis(
    val id: Int,
    val vehicleId: Int,
    val recommendedAction: String,
    val serviceFindings: List<ServiceFinding>
)

data class ServiceFinding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
