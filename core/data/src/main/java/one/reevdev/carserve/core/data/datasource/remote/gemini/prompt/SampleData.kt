package one.reevdev.carserve.core.data.datasource.remote.gemini.prompt

import one.reevdev.carserve.core.data.datasource.model.Finding
import one.reevdev.carserve.core.data.datasource.model.ServiceAnalysisResult

object SampleData {

    val serviceResult = ServiceAnalysisResult(
        vehicleId = 25,
        recommendedAction = "Take to nearest service",
        findings = listOf(
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0
            ),
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0
            ),
            Finding(
                problem = "The problem",
                solution = "The solution",
                estimatedPrice = 0.0
            ),
        )
    )
}