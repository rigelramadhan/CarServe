package one.reevdev.carserve.core.data.remote.api.prompt

import one.reevdev.carserve.core.common.data.toJson

object ResponseRule {
    private const val JSON_GENERAL_RULE= """
        Return JSON with this format:
    """

    val SERVICE_JSON = """
        $JSON_GENERAL_RULE
        
        ${SampleData.serviceResult.toJson()}
    """.trimIndent()
}