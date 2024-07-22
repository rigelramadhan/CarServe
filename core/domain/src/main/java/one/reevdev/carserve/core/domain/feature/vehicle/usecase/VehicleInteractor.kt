package one.reevdev.carserve.core.domain.feature.vehicle.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.common.data.mapFlowData
import one.reevdev.carserve.core.data.repository.vehicle.VehicleRepository
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.core.domain.utils.toDomain
import one.reevdev.carserve.core.domain.utils.toRequest
import javax.inject.Inject

class VehicleInteractor @Inject constructor(
    private val repository: VehicleRepository
) : VehicleUseCase {
    override fun saveVehicle(vehicle: Vehicle): Flow<Result<Boolean>> {
        return repository.saveVehicle(vehicle.toRequest())
    }

    override fun updateVehicle(vehicle: Vehicle): Flow<Result<Boolean>> {
        return repository.updateVehicle(vehicle.toRequest())
    }

    override fun deleteVehicle(id: Int): Flow<Result<Boolean>> {
        return repository.deleteVehicle(id)
    }

    override fun getVehicleById(id: Int): Flow<Result<Vehicle>> {
        return repository.getVehicleById(id).mapFlowData { it.toDomain() }
    }

    override fun getAllVehicles(): Flow<Result<List<Vehicle>>> {
        return repository.getAllVehicles().mapFlowData { it.map { vehicle -> vehicle.toDomain() } }
    }
}