package one.reevdev.carserve.core.data.feature.service.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import one.reevdev.carserve.core.data.feature.service.datasource.local.entity.ServiceAnalysisResultJsonEntity

@Dao
interface ServiceAnalysisDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServiceAnalysis(analysis: ServiceAnalysisResultJsonEntity)

    @Query("SELECT * FROM analysis")
    suspend fun getAllServiceAnalysis(): List<ServiceAnalysisResultJsonEntity>

    @Query("SELECT * FROM analysis WHERE id = :id")
    suspend fun getServiceAnalysisById(id: Int): ServiceAnalysisResultJsonEntity
}