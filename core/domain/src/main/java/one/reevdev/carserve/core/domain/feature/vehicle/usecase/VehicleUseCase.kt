package one.reevdev.carserve.core.domain.feature.vehicle.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

interface VehicleUseCase {

    fun saveVehicle(vehicle: Vehicle): Flow<Result<Boolean>>

    fun updateVehicle(vehicle: Vehicle): Flow<Result<Boolean>>

    fun deleteVehicle(id: Int): Flow<Result<Boolean>>

    fun getVehicleById(id: Int): Flow<Result<Vehicle>>

    fun getAllVehicles(): Flow<Result<List<Vehicle>>>
}