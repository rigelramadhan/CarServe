package one.reevdev.carserve.core.data.feature.vehicle.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity

@Dao
interface VehicleDao {

    @Insert(VehicleEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicleEntity: VehicleEntity)

    @Query("SELECT * FROM vehicle")
    suspend fun getAllVehicle(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle WHERE id = :id")
    suspend fun getVehicleById(id: Int): VehicleEntity

    @Query("DELETE FROM vehicle WHERE id = :id")
    suspend fun deleteVehicleById(id: Int)

    @Update(VehicleEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun updateVehicle(vehicleEntity: VehicleEntity)
}