package one.reevdev.carserve.core.data.datasource.model.vehicle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("vehicle")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("carName")
    val carName: String,

    @ColumnInfo("color")
    val color: String,

    @ColumnInfo("transmission")
    val transmission: String,
)
