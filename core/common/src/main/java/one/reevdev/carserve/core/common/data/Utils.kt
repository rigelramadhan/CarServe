package one.reevdev.carserve.core.common.data

import java.text.NumberFormat
import java.util.Locale

fun emptyString() = ""

fun Double.toRupiahCurrency(): String {
    val indonesiaLocale = Locale("id", "ID") // Indonesian locale
    val currencyFormatter = NumberFormat.getCurrencyInstance(indonesiaLocale)
    return currencyFormatter.format(this)
}