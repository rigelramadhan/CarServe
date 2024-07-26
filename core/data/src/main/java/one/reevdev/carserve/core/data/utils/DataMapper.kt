package one.reevdev.carserve.core.data.utils

import com.google.gson.Gson
import one.reevdev.carserve.core.common.data.toJson
import one.reevdev.carserve.core.data.feature.service.datasource.local.entity.ServiceAnalysisResultJsonEntity
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult

fun ServiceAnalysisResultJsonEntity.toKotlinObject() =
    Gson().fromJson(resultJson, ServiceAnalysisResult::class.java)

fun ServiceAnalysisResult.toJson() = ServiceAnalysisResultJsonEntity(resultJson = this.toJson())