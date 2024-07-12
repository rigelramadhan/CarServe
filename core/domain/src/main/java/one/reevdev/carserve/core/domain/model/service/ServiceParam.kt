package one.reevdev.carserve.core.domain.model.service

import android.graphics.Bitmap
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.model.vehicle.VehicleParam

data class ServiceParam(
    val symptoms: String = emptyString(),
    val generalProblem: String = emptyString(),
    val vehicle: VehicleParam = VehicleParam(),
    val photo: Bitmap? = null
)
