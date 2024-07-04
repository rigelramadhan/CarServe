package one.reevdev.carserve.core.domain.utils

import one.reevdev.carserve.core.data.datasource.model.Finding
import one.reevdev.carserve.core.data.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.ServiceParamData
import one.reevdev.carserve.core.domain.model.service.ServiceAnalysis
import one.reevdev.carserve.core.domain.model.service.ServiceFinding
import one.reevdev.carserve.core.domain.model.service.ServiceParam

fun ServiceAnalysisResult.toDomain() = ServiceAnalysis(
    id = id,
    vehicleId = vehicleId,
    recommendedAction = recommendedAction,
    serviceFindings = findings.map { it.toDomain() }
)

fun Finding.toDomain() = ServiceFinding(
    problem = problem,
    solution = solution,
    estimatedPrice = estimatedPrice
)

fun ServiceParam.toRequest() = ServiceParamData(
    symptoms = symptoms,
    generalProblem = generalProblem,
    photo = photo
)