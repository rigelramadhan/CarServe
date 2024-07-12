package one.reevdev.carserve.core.data.datasource.model.service

import android.graphics.Bitmap
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleParamData

data class ServiceParamData(
    val symptoms: String,
    val generalProblem: String,
    val vehicle: VehicleParamData,
    val photo: Bitmap? = null
)
