package one.reevdev.carserve.core.domain.usecase.vehicle

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.domain.model.Result
import one.reevdev.carserve.core.domain.model.vehicle.Vehicle

interface VehicleUseCase {

    fun saveVehicle(vehicle: Vehicle): Flow<Result<Boolean>>

    fun updateVehicle(vehicle: Vehicle): Flow<Result<Boolean>>

    fun deleteVehicle(id: Int): Flow<Result<Boolean>>

    fun getVehicleById(id: Int): Flow<Result<Vehicle>>

    fun getAllVehicles(): Flow<Result<List<Vehicle>>>
}