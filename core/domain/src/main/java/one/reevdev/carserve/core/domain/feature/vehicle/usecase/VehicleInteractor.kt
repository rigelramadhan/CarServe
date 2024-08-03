package one.reevdev.carserve.core.domain.feature.vehicle.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.mapFlowData
import one.reevdev.carserve.core.data.feature.vehicle.repository.VehicleRepository
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.core.domain.utils.toDomain
import one.reevdev.carserve.core.domain.utils.toEntity
import javax.inject.Inject

class VehicleInteractor @Inject constructor(
    private val repository: VehicleRepository
) : VehicleUseCase {
    override fun saveCustomerVehicle(vehicle: CustomerVehicle): Flow<Result<Boolean>> {
        return repository.saveCustomerVehicle(vehicle.toEntity())
    }

    override fun updateCustomerVehicle(vehicle: CustomerVehicle): Flow<Result<Boolean>> {
        return repository.updateCustomerVehicle(vehicle.toEntity())
    }

    override fun deleteCustomerVehicle(policeNo: String): Flow<Result<Boolean>> {
        return repository.deleteCustomerVehicle(policeNo)
    }

    override fun getCustomerVehicleByPoliceNo(policeNo: String): Flow<Result<CustomerVehicle>> {
        return repository.getCustomerVehicleById(policeNo).mapFlowData { it.toDomain() }
    }

    override fun getAllCustomerVehicles(): Flow<Result<List<CustomerVehicle>>> {
        return repository.getAllCustomerVehicles().mapFlowData { it.map { vehicle -> vehicle.toDomain() } }
    }

    override fun getAllVehicles(): Flow<Result<List<Vehicle>>> {
        return repository.getAllVehicles().mapFlowData { it.map { vehicle -> vehicle.toDomain() } }
    }
}