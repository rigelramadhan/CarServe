package one.reevdev.carserve.core.data.feature.vehicle.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.CustomerVehicleEntity
import one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model.VehicleEntity

@Database(
    entities = [VehicleEntity::class, CustomerVehicleEntity::class],
    version = 2,
    exportSchema = true
)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun dao(): VehicleDao
}