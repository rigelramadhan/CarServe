package one.reevdev.carserve.core.data.feature.vehicle.datasource.local

import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.database.VehicleDao
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleLocalDataSource @Inject constructor(
    private val vehicleDao: VehicleDao,
) {

    suspend fun insertCustomerVehicle(customerVehicleEntity: CustomerVehicleEntity) =
        vehicleDao.insertCustomerVehicle(customerVehicleEntity)

    suspend fun getAllCustomerVehicle(): List<CustomerVehicleEntity> =
        vehicleDao.getAllCustomerVehicle()

    suspend fun getCustomerVehicleByPoliceNo(policeNo: String) =
        vehicleDao.getCustomerVehicleByPoliceNo(policeNo)

    suspend fun deleteCustomerVehicleByPoliceNo(policeNo: String) =
        vehicleDao.deleteCustomerVehicleByPoliceNo(policeNo)

    suspend fun updateCustomerVehicle(customerVehicleEntity: CustomerVehicleEntity) =
        vehicleDao.updateCustomerVehicle(customerVehicleEntity)

}