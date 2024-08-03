package one.reevdev.carserve.core.data.feature.vehicle.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.VehicleLocalDataSource
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity
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
    override fun saveCustomerVehicle(vehicle: CustomerVehicleEntity): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            localDataSource.insertCustomerVehicle(vehicle)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun updateCustomerVehicle(vehicle: CustomerVehicleEntity): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            localDataSource.updateCustomerVehicle(vehicle)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun deleteCustomerVehicle(policeNo: String): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        try {
            localDataSource.deleteCustomerVehicleByPoliceNo(policeNo)
            emit(Result.Success(true))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun getCustomerVehicleById(policeNo: String): Flow<Result<CustomerVehicleEntity>> = flow {
        emit(Result.Loading())
        try {
            val data = localDataSource.getCustomerVehicleByPoliceNo(policeNo)
            emit(Result.Success(data))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

    override fun getAllCustomerVehicles(): Flow<Result<List<CustomerVehicleEntity>>> = flow {
        emit(Result.Loading())
        try {
            val data = localDataSource.getAllCustomerVehicle()
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