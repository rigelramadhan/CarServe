package one.reevdev.carserve.core.domain.feature.profile.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.profile.model.Customer

interface ProfileUseCase {

    fun saveCustomer(param: Customer) : Flow<Result<Boolean>>

    fun getAllCustomers() : Flow<Result<List<Customer>>>

    fun getCustomerByEmail(email: String) : Flow<Result<Customer>>

    fun getServiceAdvisorData() : Flow<Result<String>>
}