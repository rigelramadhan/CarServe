package one.reevdev.carserve.core.data.feature.service.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import one.reevdev.carserve.core.data.feature.service.datasource.local.entity.ServiceAnalysisResultJsonEntity

@Database(entities = [ServiceAnalysisResultJsonEntity::class], version = 1)
abstract class ServiceAnalysisDatabase : RoomDatabase() {

    abstract fun dao(): ServiceAnalysisDao
}