package one.reevdev.carserve.core.data.repository.vehicle

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.data.datasource.model.VehicleData

interface VehicleRepository {

    fun saveVehicle(vehicle: VehicleData): Flow<Result<Boolean>>

    fun updateVehicle(vehicle: VehicleData): Flow<Result<Boolean>>

    fun deleteVehicle(id: Int): Flow<Result<Boolean>>

    fun getVehicleById(id: Int): Flow<Result<VehicleData>>

    fun getAllVehicles(): Flow<Result<List<VehicleData>>>
}