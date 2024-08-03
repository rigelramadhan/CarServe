package one.reevdev.carserve.core.data.feature.profile.repository

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.feature.profile.datasource.model.Customer

interface ProfileRepository {

    fun saveCustomer(param: Customer) : Flow<Result<Boolean>>

    fun getLastCustomer() : Flow<Result<Customer>>

    fun getCustomerByEmail(email: String) : Flow<Result<Customer>>

    fun getServiceAdvisorData() : Flow<Result<String>>
}