package one.reevdev.carserve.core.data.feature.service.datasource.local

import one.reevdev.carserve.core.data.feature.service.datasource.local.database.ServiceAnalysisDao
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.utils.toJson
import one.reevdev.carserve.core.data.utils.toKotlinObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceLocalDataSource @Inject constructor(
    private val dao: ServiceAnalysisDao
) {

    suspend fun insertServiceAnalysis(analysis: ServiceAnalysisResult) {
        dao.insertServiceAnalysis(analysis.toJson())
    }

    suspend fun getAllServiceAnalysis(): List<ServiceAnalysisResult> {
        return dao.getAllServiceAnalysis().map { it.toKotlinObject() }
    }

    suspend fun getServiceAnalysisById(id: Int): ServiceAnalysisResult {
        return dao.getServiceAnalysisById(id).toKotlinObject()
    }
}