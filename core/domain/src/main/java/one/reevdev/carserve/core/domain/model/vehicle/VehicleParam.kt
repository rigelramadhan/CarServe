package one.reevdev.carserve.core.domain.model.vehicle

import one.reevdev.carserve.core.common.data.emptyString

data class VehicleParam(
    val carName: String = emptyString(),
    val color: String = emptyString(),
    val transmission: String = emptyString(),
)
