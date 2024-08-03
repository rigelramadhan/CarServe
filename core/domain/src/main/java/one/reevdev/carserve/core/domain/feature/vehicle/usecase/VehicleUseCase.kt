package one.reevdev.carserve.core.domain.feature.vehicle.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle

interface VehicleUseCase {

    fun saveCustomerVehicle(vehicle: CustomerVehicle): Flow<Result<Boolean>>

    fun updateCustomerVehicle(vehicle: CustomerVehicle): Flow<Result<Boolean>>

    fun deleteCustomerVehicle(policeNo: String): Flow<Result<Boolean>>

    fun getCustomerVehicleByPoliceNo(policeNo: String): Flow<Result<CustomerVehicle>>

    fun getAllCustomerVehicles(): Flow<Result<List<CustomerVehicle>>>

    fun getAllVehicles(): Flow<Result<List<Vehicle>>>
}