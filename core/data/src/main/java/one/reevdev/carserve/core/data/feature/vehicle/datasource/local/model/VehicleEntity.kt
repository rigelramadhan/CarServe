package one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("vehicle")
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,

    @ColumnInfo("carBrand")
    val carBrand: String,

    @ColumnInfo("carName")
    val carName: String,

    @ColumnInfo("carType")
    val carType: String,

    @ColumnInfo("color")
    val color: String,

    @ColumnInfo("transmission")
    val transmission: String,
)
