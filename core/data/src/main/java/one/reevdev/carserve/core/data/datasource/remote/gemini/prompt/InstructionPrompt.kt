package one.reevdev.carserve.core.data.datasource.remote.gemini.prompt

import one.reevdev.carserve.core.data.datasource.model.service.AvailableService
import one.reevdev.carserve.core.data.datasource.model.vehicle.VehicleParamData

object InstructionPrompt {

    fun initializeServiceAnalysisResponse() = """
        From this point on, you will be asked about the service the user want you to analyze. 
        Give response as I instructed.
        
    """.trimIndent()

    fun analyzeCar(
        symptoms: String,
        problem: String,
        vehicle: VehicleParamData,
        availableService: List<AvailableService>
    ) = """
        I have this image and symptoms taken and I want you to analyze it with these points:
        1. The findings (can be more than 1, each consists of the problem, solution, and estimated price)
        2. The recommended action generally
        
        Prioritize picking the solution from this list:
        ${availableService.ifEmpty { SampleData.servicesList }}
        
        If the image is not a car, tell the user that it's not a car but still give findings and recommendation
        based on the given symptoms and general problem.
        
        Here's the car information.
        Car name: ${vehicle.carName}
        Color: ${vehicle.color}
        Transmission: ${vehicle.transmission}
        
        Here are the symptoms and problems I found.
        Symptoms: $symptoms
        Other complaints: $problem
        
        ${ResponseRule.SERVICE_JSON}
        
        Create a thorough and easy to read comprehensive analysis in the html format to fill the 
        analysisHtml data with beautiful but professional formatting.
    """.trimIndent()
}