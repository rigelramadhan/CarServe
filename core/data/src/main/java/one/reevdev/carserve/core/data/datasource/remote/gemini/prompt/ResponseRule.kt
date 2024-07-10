package one.reevdev.carserve.core.data.datasource.remote.gemini.prompt

import one.reevdev.carserve.core.common.data.toJson

object ResponseRule {
    private const val JSON_GENERAL_RULE= """
        Please ALWAYS return a RAW JSON response without any formatting. Return success with the 
        following STRUCTURE (ignore the values) because it will be deserialized by GSON:
    """

    val SERVICE_JSON = """
        $JSON_GENERAL_RULE
        
        ${SampleData.serviceResult.toJson()}
    """.trimIndent()
}