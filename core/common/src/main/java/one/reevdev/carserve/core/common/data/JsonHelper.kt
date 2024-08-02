package one.reevdev.carserve.core.common.data

import com.google.gson.Gson

fun <T> String.jsonToObject(className: Class<T>): T = Gson().fromJson(this, className)

fun Any.toJson(): String = Gson().toJson(this)