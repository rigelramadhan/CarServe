package one.reevdev.carserve.core.data.feature.vehicle.datasource.local

import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.database.VehicleDao
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val vehicleDao: VehicleDao
) {
    suspend fun insertVehicle(vehicleEntity: VehicleEntity) = vehicleDao.insertVehicle(vehicleEntity)

    suspend fun getAllVehicle(): List<VehicleEntity> = vehicleDao.getAllVehicle()

    suspend fun getVehicleById(id: Int) = vehicleDao.getVehicleById(id)

    suspend fun deleteVehicleById(id: Int) = vehicleDao.deleteVehicleById(id)

    suspend fun updateVehicle(vehicleEntity: VehicleEntity) = vehicleDao.updateVehicle(vehicleEntity)
}