package one.reevdev.carserve.core.data.feature.profile.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datastore.AuthPreferences
import one.reevdev.carserve.core.data.feature.profile.datasource.local.CustomerLocalDataSource
import one.reevdev.carserve.core.data.feature.profile.datasource.local.entity.CustomerEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val authPreferences: AuthPreferences,
    private val localDataSource: CustomerLocalDataSource
) : ProfileRepository {

    override fun saveCustomer(param: CustomerEntity): Flow<Result<Boolean>> = flow {
        emit(Result.Loading())
        localDataSource.insertCustomer(param)
        emit(Result.Success(true))
    }

    override fun getAllCustomers(): Flow<Result<List<CustomerEntity>>> = flow {
        emit(Result.Loading())
        val data = localDataSource.getAllCustomers()
        emit(Result.Success(data))
    }

    override fun getCustomerByEmail(email: String): Flow<Result<CustomerEntity>> = flow {
        emit(Result.Loading())
        val data = localDataSource.getCustomerByEmail(email)
        if (data != null) {
            emit(Result.Success(data))
        } else {
            emit(Result.Error(Exception("Customer not found")))
        }
    }

    override fun getServiceAdvisorData(): Flow<Result<String>> {
        return authPreferences.getUserEmail().map { Result.Success(it) }
    }
}