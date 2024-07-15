package one.reevdev.carserve.core.domain.feature.vehicle.model

import one.reevdev.carserve.core.common.data.emptyString

data class VehicleParam(
    val carName: String = emptyString(),
    val color: String = emptyString(),
    val transmission: String = emptyString(),
)
