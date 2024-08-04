package one.reevdev.carserve.core.data.feature.service.datasource.model

import android.graphics.Bitmap
import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val vehicle: CustomerVehicleEntity,
    val profile: CustomerEntity,
    val photo: Bitmap? = null
)
