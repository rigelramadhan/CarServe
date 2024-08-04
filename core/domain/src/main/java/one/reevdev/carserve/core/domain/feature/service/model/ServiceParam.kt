package one.reevdev.carserve.core.domain.feature.service.model

import android.graphics.Bitmap
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle

data class ServiceParam(
    val symptoms: String = emptyString(),
    val generalProblem: String = emptyString(),
    val vehicle: CustomerVehicle = CustomerVehicle(),
    val profile: Customer = Customer(),
    val photo: Bitmap? = null
)
