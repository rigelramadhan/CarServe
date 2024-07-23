package one.reevdev.carserve.core.domain.utils

import one.reevdev.carserve.core.data.datasource.model.auth.LoginParamData
import one.reevdev.carserve.core.data.datasource.model.service.Finding
import one.reevdev.carserve.core.data.datasource.model.service.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.service.ServiceParamData
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity
import one.reevdev.carserve.core.domain.feature.auth.model.LoginParam
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.service.model.ServiceParam
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

fun ServiceAnalysisResult.toDomain() = ServiceAnalysis(
    vehicle = vehicle.toDomain(),
    recommendedAction = recommendedAction,
    serviceFindings = findings.map { it.toDomain() },
    totalEstimatedPrice = findings.sumOf { it.estimatedPrice },
    analysisHtml = analysisHtml
)

fun ServiceAnalysis.toRequest() = ServiceAnalysisResult(
    vehicle = vehicle?.toRequest() ?: Vehicle().toRequest(),
    recommendedAction = recommendedAction,
    findings = serviceFindings.map { it.toRequest() },
    analysisHtml = analysisHtml
)

fun Finding.toDomain() = ServiceFinding(
    problem = problem,
    solution = solution,
    estimatedPrice = estimatedPrice
)

fun ServiceFinding.toRequest() = Finding(
    problem = problem,
    solution = solution,
    estimatedPrice = estimatedPrice
)

fun ServiceParam.toRequest() = ServiceParamData(
    symptoms = symptoms,
    generalProblem = generalProblem,
    vehicle = vehicle.toRequest(),
    photo = photo
)

fun VehicleEntity.toDomain() = Vehicle(
    id = id,
    carName = carName,
    color = color,
    transmission = transmission
)

fun Vehicle.toRequest() = VehicleEntity(
    id = id,
    carName = carName,
    color = color,
    transmission = transmission
)

fun LoginParam.toRequest() = LoginParamData(
    email = email,
    password = password
)