package one.reevdev.carserve.core.data.feature.vehicle.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer_vehicle")
data class CustomerVehicleEntity(
    @PrimaryKey
    @ColumnInfo("policeNo")
    val policeNo: String,

    @ColumnInfo("ownerEmail")
    val ownerEmail: String,

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
