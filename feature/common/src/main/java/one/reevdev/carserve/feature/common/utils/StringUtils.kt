package one.reevdev.carserve.feature.common.utils

fun String.isValidEmail(): Boolean {
    val emailPattern = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+\$"
    return this.matches(emailPattern.toRegex())
}