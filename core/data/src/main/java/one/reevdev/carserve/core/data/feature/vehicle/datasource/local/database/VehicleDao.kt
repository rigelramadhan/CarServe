package one.reevdev.carserve.core.data.feature.vehicle.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity

@Dao
interface VehicleDao {

    @Insert(CustomerVehicleEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerVehicle(customerVehicleEntity: CustomerVehicleEntity)

    @Query("SELECT * FROM customer_vehicle")
    suspend fun getAllCustomerVehicle(): List<CustomerVehicleEntity>

    @Query("SELECT * FROM customer_vehicle WHERE policeNo = :policeNo")
    suspend fun getCustomerVehicleByPoliceNo(policeNo: String): CustomerVehicleEntity

    @Query("DELETE FROM customer_vehicle WHERE policeNo = :policeNo")
    suspend fun deleteCustomerVehicleByPoliceNo(policeNo: String)

    @Update(CustomerVehicleEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun updateCustomerVehicle(customerVehicleEntity: CustomerVehicleEntity)

}