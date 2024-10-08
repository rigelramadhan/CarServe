package one.reevdev.carserve.core.data.remote.api

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import one.reevdev.carserve.core.common.data.jsonToObject
import one.reevdev.carserve.core.data.BuildConfig
import one.reevdev.carserve.core.data.feature.service.datasource.model.AvailableService
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceAnalysisResult
import one.reevdev.carserve.core.data.feature.service.datasource.model.ServiceParamData
import one.reevdev.carserve.core.data.remote.api.prompt.InstructionPrompt
import one.reevdev.carserve.core.data.utils.toContent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGeminiApi @Inject constructor() {
    private val generativeModel: GenerativeModel by lazy {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = BuildConfig.apiKey,
            generationConfig = generationConfig {
                responseMimeType = "application/json"
            },
            systemInstruction = content {
                text(InstructionPrompt.initializeServiceAnalysisResponse())
            }
        )
    }

    private val chatHistory: MutableList<Content> = mutableListOf()

    suspend fun analyzeService(
        param: ServiceParamData,
        availableServices: List<AvailableService>
    ): ServiceAnalysisResult {
        val chat = generativeModel.startChat(chatHistory)
        val instruction = InstructionPrompt.analyzeCar(
            symptoms = param.symptoms,
            problem = param.generalProblem,
            vehicle = param.vehicle,
            profile = param.profile,
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