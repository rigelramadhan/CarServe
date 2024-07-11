package one.reevdev.carserve.core.data.datasource.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import one.reevdev.carserve.core.common.data.Result
import one.reevdev.carserve.core.data.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.datasource.remote.firestore.FirestoreClient
import one.reevdev.carserve.core.data.datasource.remote.gemini.ServiceGeminiApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val geminiApi: ServiceGeminiApi,
    private val firestore: FirestoreClient
) {

    fun analyzeService(param: ServiceParamData): Flow<Result<ServiceAnalysisResult>> = channelFlow {
        send(Result.Loading())
        try {
            firestore.getAllAvailableService().collectLatest {
                if (it is Result.Success) {
                    val data = geminiApi.analyzeService(param, it.data)
                    send(Result.Success(data))
                }
            }
        } catch (e: Exception) {
            send(Result.Error(e))
        }
    }
}