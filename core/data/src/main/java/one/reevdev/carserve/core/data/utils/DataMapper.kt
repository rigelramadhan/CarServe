package one.reevdev.carserve.core.data.utils

import com.google.gson.Gson
import one.reevdev.carserve.core.common.data.toJson
import one.reevdev.carserve.core.data.feature.service.datasource.local.entity.ServiceAnalysisResultJsonEntity
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.remote.model.VehicleResponse

fun ServiceAnalysisResultJsonEntity.toKotlinObject() =
    Gson().fromJson(resultJson, ServiceAnalysisResult::class.java)

fun ServiceAnalysisResult.toJson() = ServiceAnalysisResultJsonEntity(resultJson = this.toJson())

fun VehicleResponse.toEntity() = VehicleEntity(
    carBrand = carBrand,
    carName = carName,
    carType = carType,
    color = color,
    transmission = transmission
)