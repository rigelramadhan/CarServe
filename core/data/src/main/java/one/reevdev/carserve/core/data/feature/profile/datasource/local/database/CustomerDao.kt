package one.reevdev.carserve.core.data.feature.profile.datasource.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customerEntity: CustomerEntity)

    @Query("SELECT * FROM customer WHERE email = :email")
    suspend fun getCustomerByEmail(email: String): CustomerEntity?

    @Query("SELECT * FROM customer")
    suspend fun getAllCustomers(): List<CustomerEntity>
}