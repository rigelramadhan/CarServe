package one.reevdev.carserve.core.data.feature.profile.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity

interface ProfileRepository {

    fun saveCustomer(param: CustomerEntity) : Flow<Result<Boolean>>

    fun getAllCustomers() : Flow<Result<List<CustomerEntity>>>

    fun getCustomerByEmail(email: String) : Flow<Result<CustomerEntity>>

    fun getServiceAdvisorData() : Flow<Result<String>>
}