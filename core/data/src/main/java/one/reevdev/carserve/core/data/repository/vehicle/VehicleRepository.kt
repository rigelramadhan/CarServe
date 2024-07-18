package one.reevdev.carserve.core.data.repository.vehicle

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity

interface VehicleRepository {

    fun saveVehicle(vehicle: VehicleEntity): Flow<Result<Boolean>>

    fun updateVehicle(vehicle: VehicleEntity): Flow<Result<Boolean>>

    fun deleteVehicle(id: Int): Flow<Result<Boolean>>

    fun getVehicleById(id: Int): Flow<Result<VehicleEntity>>

    fun getAllVehicles(): Flow<Result<List<VehicleEntity>>>
}