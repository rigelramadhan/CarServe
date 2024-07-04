package one.reevdev.carserve.core.data.utils

import com.google.ai.client.generativeai.type.content

fun String.toContent() = content { text(this@toContent) }