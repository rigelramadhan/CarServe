package one.reevdev.carserve.core.data.datasource.remote.gemini.prompt

object InstructionPrompt {

    fun analyzeCar(symptoms: String, problem: String) = """
        I have this image and symptoms taken and I want you to analyze it with these points:
        1. The findings (can be more than 1, each consists of the problem, solution, and estimated price)
        2. The recommended action generally
        
        Here are the symptoms and problems I found.
        Symptoms: $symptoms
        General problem: $problem
        
        ${ResponseRule.SERVICE_JSON}
    """.trimIndent() // TODO: Make the prompts
}