package one.reevdev.carserve.core.data.feature.vehicle.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity

interface VehicleRepository {

    fun saveVehicle(vehicle: VehicleEntity): Flow<Result<Boolean>>

    fun updateVehicle(vehicle: VehicleEntity): Flow<Result<Boolean>>

    fun deleteVehicle(id: Int): Flow<Result<Boolean>>

    fun getVehicleById(id: Int): Flow<Result<VehicleEntity>>

    fun getAllSavedVehicles(): Flow<Result<List<VehicleEntity>>>

    fun getAllVehicles(): Flow<Result<List<VehicleEntity>>>
}