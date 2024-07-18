package one.reevdev.carserve.core.data.datasource.local.vehicle

import androidx.room.Database
import androidx.room.RoomDatabase
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleEntity

@Database(entities = [VehicleEntity::class], version = 1, exportSchema = true)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun dao(): VehicleDao
}