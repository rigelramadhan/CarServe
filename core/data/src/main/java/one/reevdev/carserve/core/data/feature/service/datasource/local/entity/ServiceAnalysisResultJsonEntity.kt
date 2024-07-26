package one.reevdev.carserve.core.data.feature.service.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("analysis")
data class ServiceAnalysisResultJsonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "result_json")
    val resultJson: String,
)
