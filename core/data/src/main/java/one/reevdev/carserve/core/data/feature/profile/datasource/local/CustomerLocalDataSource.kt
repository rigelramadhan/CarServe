package one.reevdev.carserve.core.data.feature.profile.datasource.local

import one.reevdev.carserve.core.data.feature.profile.datasource.local.database.CustomerDao
import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerLocalDataSource @Inject constructor(
    private val dao: CustomerDao
) {
    suspend fun insertCustomer(customerEntity: CustomerEntity) {
        return dao.insertCustomer(customerEntity)
    }

    suspend fun getCustomerByEmail(email: String): CustomerEntity? {
        return dao.getCustomerByEmail(email)
    }

    suspend fun getAllCustomers(): List<CustomerEntity> {
        return dao.getAllCustomers()
    }
}