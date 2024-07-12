package one.reevdev.carserve.core.data.datasource.remote.gemini

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import one.reevdev.carserve.core.common.data.jsonToObject
import one.reevdev.carserve.core.data.BuildConfig
import one.reevdev.carserve.core.data.datasource.model.service.AvailableService
import one.reevdev.carserve.core.data.datasource.model.service.ServiceAnalysisResult
import one.reevdev.carserve.core.data.datasource.model.service.ServiceParamData
import one.reevdev.carserve.core.data.datasource.remote.gemini.prompt.InstructionPrompt
import one.reevdev.carserve.core.data.utils.toContent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGeminiApi @Inject constructor() {
    private val generativeModel: GenerativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.apiKey
        )
    }

    private val chatHistory: MutableList<Content> = mutableListOf()

    init {
        chatHistory.add(
            content {
                text(InstructionPrompt.initializeServiceAnalysisResponse())
            }
        )
    }

    suspend fun analyzeService(
        param: ServiceParamData,
        availableServices: List<AvailableService>
    ): ServiceAnalysisResult {
        val chat = generativeModel.startChat(chatHistory)
        val instruction = InstructionPrompt.analyzeCar(
            symptoms = param.symptoms,
            problem = param.generalProblem,
            vehicle = param.vehicle,
            availableService = availableServices
        )

        val content = content {
            text(instruction)
            param.photo?.let { image(it) }
        }

        val response = chat.sendMessage(content).text.orEmpty()
        chatHistory.apply {
            add(content)
            add(response.toContent())
        }
        val data = response.jsonToObject(ServiceAnalysisResult::class.java)
        return data
    }
}