package one.reevdev.carserve.core.data.feature.service.datasource.local

import one.reevdev.carserve.core.data.feature.service.datasource.local.database.ServiceAnalysisDao
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.utils.toJson
import one.reevdev.carserve.core.data.utils.toKotlinObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceLocalDataSource @Inject constructor(
    private val dao: ServiceAnalysisDao
) {

    suspend fun insertServiceAnalysis(analysis: ServiceAnalysisResult) {
        dao.insertServiceAnalysis(analysis.toJson())
    }

    suspend fun getAllRecentServiceAnalysis(): List<ServiceAnalysisResult> {
        return dao.getAllServiceAnalysis().map { it.toKotlinObject() }.sortedByDescending {
            val formatter = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
            formatter.parse(it.createDate) as Date
        }
    }

    suspend fun getServiceAnalysisById(id: Int): ServiceAnalysisResult {
        return dao.getServiceAnalysisById(id).toKotlinObject()
    }
}