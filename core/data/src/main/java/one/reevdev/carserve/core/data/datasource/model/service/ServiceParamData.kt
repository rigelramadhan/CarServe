package one.reevdev.carserve.core.data.datasource.model.service

import android.graphics.Bitmap
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val vehicle: VehicleEntity,
    val photo: Bitmap? = null
)
