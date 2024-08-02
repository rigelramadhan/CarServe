package one.reevdev.carserve.core.data.feature.vehicle.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.VehicleLocalDataSource
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.remote.VehicleRemoteDataSource
import one.reevdev.carserve.core.data.utils.toEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepositoryImpl @Inject constructor(
    private val localDataSource: VehicleLocalDataSource,
    private val remoteDataSource: VehicleRemoteDataSource
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

    override fun getAllSavedVehicles(): Flow<Result<List<VehicleEntity>>> = flow {
        emit(Result.Loading())
        try {
            val data = localDataSource.getAllVehicle()
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun getAllVehicles(): Flow<Result<List<VehicleEntity>>> = flow {
        emit(Result.Loading())
        try {
            val data = remoteDataSource.getVehicles().map { it.toEntity() }
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}