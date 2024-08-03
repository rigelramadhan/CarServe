package one.reevdev.carserve.core.domain.utils

import one.reevdev.carserve.core.data.feature.auth.datasource.model.LoginParamData
import one.reevdev.carserve.core.data.feature.profile.datasource.model.Customer
import one.reevdev.carserve.core.data.feature.service.datasource.model.Finding
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity
import one.reevdev.carserve.core.domain.feature.auth.model.LoginParam
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.service.model.ServiceParam
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

fun ServiceAnalysisResult.toDomain() = ServiceAnalysis(
    vehicle = vehicle.toDomain(),
    profile = profile.toDomain(),
    recommendedAction = recommendedAction,
    serviceFindings = findings.map { it.toDomain() },
    totalEstimatedPrice = findings.sumOf { it.estimatedPrice },
    analysisHtml = analysisHtml
)

fun ServiceAnalysis.toRequest() = ServiceAnalysisResult(
    vehicle = vehicle?.toRequest() ?: Vehicle().toRequest(),
    profile = profile?.toRequest() ?: SavedProfile().toRequest(),
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
    profile = profile.toRequest(),
    photo = photo
)

fun VehicleEntity.toDomain() = Vehicle(
    id = id,
    carBrand = carBrand,
    carName = carName,
    carType = carType,
    color = color,
    transmission = transmission
)

fun Vehicle.toRequest() = VehicleEntity(
    id = id,
    carBrand = carBrand,
    carName = carName,
    carType = carType,
    color = color,
    transmission = transmission
)

fun CustomerVehicleEntity.toDomain() = CustomerVehicle(
    policeNo = policeNo,
    ownerEmail = ownerEmail,
    carBrand = carBrand,
    carName = carName,
    carType = carType,
    color = color,
    transmission = transmission
)

fun CustomerVehicle.toEntity() = CustomerVehicleEntity(
    policeNo = policeNo,
    ownerEmail = ownerEmail,
    carBrand = carBrand,
    carName = carName,
    carType = carType,
    color = color,
    transmission = transmission
)

fun LoginParam.toRequest() = LoginParamData(
    email = email,
    password = password
)

fun SavedProfile.toRequest() = Customer(
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    address = address
)

fun Customer.toDomain() = SavedProfile(
    name = name,
    email = email,
    phoneNumber = phoneNumber,
    address = address
)