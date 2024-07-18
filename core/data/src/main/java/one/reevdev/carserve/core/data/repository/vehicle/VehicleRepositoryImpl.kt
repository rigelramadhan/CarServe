package one.reevdev.carserve.core.data.repository.vehicle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.local.LocalDataSource
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
) : VehicleRepository {
    override fun saveVehicle(vehicle: VehicleEntity): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            localDataSource.insertVehicle(vehicle)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun updateVehicle(vehicle: VehicleEntity): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            localDataSource.updateVehicle(vehicle)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun deleteVehicle(id: Int): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            localDataSource.deleteVehicleById(id)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun getVehicleById(id: Int): Flow<Result<VehicleEntity>> = flow {
        emit(Result.Loading())
        try {
            val data = localDataSource.getVehicleById(id)
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun getAllVehicles(): Flow<Result<List<VehicleEntity>>> = flow {
        emit(Result.Loading())
        try {
            val data = localDataSource.getAllVehicle()
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}