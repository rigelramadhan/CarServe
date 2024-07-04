package one.reevdev.carserve.core.data.datasource.model

data class ServiceAnalysisResult(
    val id: Int = 0,
    val vehicleId: Int,
    val recommendedAction: String,
    val findings: List<Finding>
)

data class Finding(
    val problem: String,
    val solution: String,
    val estimatedPrice: Double,
)
