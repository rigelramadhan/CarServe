package one.reevdev.carserve.core.domain.feature.profile.usecase

import kotlinx.coroutines.flow.Flow
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.domain.feature.profile.model.Customer

interface ProfileUseCase {

    fun saveLastProfileData(param: Customer) : Flow<Result<Boolean>>

    fun getLastProfileData() : Flow<Result<Customer>>

    fun getServiceAdvisorData() : Flow<Result<String>>
}