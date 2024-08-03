package one.reevdev.carserve.core.data.feature.profile.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datastore.AuthPreferences
import one.reevdev.carserve.core.data.datastore.ProfilePreferences
import one.reevdev.carserve.core.data.feature.profile.datasource.model.Customer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val profilePreferences: ProfilePreferences,
    private val authPreferences: AuthPreferences,
) : ProfileRepository {

    override fun saveCustomer(param: Customer): Flow<Result<Boolean>> = flow {
        profilePreferences.run {
            setLastUserName(param.name)
            setLastUserEmail(param.email)
            setLastUserPhoneNumber(param.phoneNumber)
            setLastUserAddress(param.address)
        }
        emit(Result.Success(true))
    }

    override fun getLastCustomer(): Flow<Result<Customer>> = flow {
        profilePreferences.run {
            combine(
                getLastUserName(),
                getLastUserEmail(),
                getLastUserPhoneNumber(),
                getLastUserAddress()
            ) { name, email, phoneNumber, address ->
                Customer(name, email, phoneNumber, address)
            }.collect {
                emit(Result.Success(it))
            }
        }
    }

    override fun getCustomerByEmail(email: String): Flow<Result<Customer>> {
        TODO("Not yet implemented")
    }

    override fun getServiceAdvisorData(): Flow<Result<String>> {
        return authPreferences.getUserEmail().map { Result.Success(it) }
    }
}