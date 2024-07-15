package one.reevdev.carserve.core.domain.feature.service.model

import android.graphics.Bitmap
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.vehicle.model.VehicleParam

data class ServiceParam(
    val symptoms: String = emptyString(),
    val generalProblem: String = emptyString(),
    val vehicle: VehicleParam = VehicleParam(),
    val photo: Bitmap? = null
)
