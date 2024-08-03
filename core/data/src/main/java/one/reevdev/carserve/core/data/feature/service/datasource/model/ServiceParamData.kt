package one.reevdev.carserve.core.data.feature.service.datasource.model

import android.graphics.Bitmap
import one.reevdev.carserve.core.data.feature.profile.datasource.model.Customer
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val vehicle: VehicleEntity,
    val profile: Customer,
    val photo: Bitmap? = null
)
