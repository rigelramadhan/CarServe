package one.reevdev.carserve.core.data.feature.profile.datasource.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customer")
data class CustomerEntity(

    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String,

    @ColumnInfo(name = "address")
    val address: String,
)
