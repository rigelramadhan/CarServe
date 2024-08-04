package one.reevdev.carserve.core.data.feature.vehicle.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity

interface VehicleRepository {

    fun saveCustomerVehicle(vehicle: CustomerVehicleEntity): Flow<Result<Boolean>>

    fun updateCustomerVehicle(vehicle: CustomerVehicleEntity): Flow<Result<Boolean>>

    fun deleteCustomerVehicle(policeNo: String): Flow<Result<Boolean>>

    fun getCustomerVehicleById(policeNo: String): Flow<Result<CustomerVehicleEntity>>

    fun getAllCustomerVehicles(): Flow<Result<List<CustomerVehicleEntity>>>

    fun getAllVehicles(): Flow<Result<List<VehicleEntity>>>
}