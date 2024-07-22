package one.reevdev.carserve.core.data.datasource.local

import one.reevdev.carserve.core.data.datasource.local.vehicle.VehicleDao
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity
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