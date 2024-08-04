package one.reevdev.carserve.core.data.feature.profile.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity

@Database(entities = [CustomerEntity::class], version = 1, exportSchema = false)
abstract class CustomerDatabase : RoomDatabase() {

    abstract fun dao(): CustomerDao
}