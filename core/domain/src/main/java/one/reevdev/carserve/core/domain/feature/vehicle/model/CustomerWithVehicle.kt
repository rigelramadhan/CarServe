package one.reevdev.carserve.core.domain.feature.vehicle.model

import one.reevdev.carserve.core.domain.feature.profile.model.Customer

data class CustomerWithVehicle(
    val customerEntity: Customer,
    val vehicles: CustomerVehicle
)
