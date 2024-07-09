package one.reevdev.carserve.core.data.datasource.remote.gemini.prompt

object InstructionPrompt {

    fun initializeServiceAnalysisResponse() = """
        From this point on, you will be asked about the service the user want you to analyze. 
        Give response as I instructed.
        
        Remember this list of services:
        ${SampleData.servicesList}
    """.trimIndent()

    fun analyzeCar(symptoms: String, problem: String) = """
        I have this image and symptoms taken and I want you to analyze it with these points:
        1. The findings (can be more than 1, each consists of the problem, solution, and estimated price)
        2. The recommended action generally
        
        Based on this data:
        ${SampleData.servicesList}
        
        Here are the symptoms and problems I found.
        Symptoms: $symptoms
        General problem: $problem
        
        ${ResponseRule.SERVICE_JSON}
    """.trimIndent() // TODO: Make the prompts
}