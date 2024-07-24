package one.reevdev.carserve.feature.common.utils

fun String.isValidEmail(): Boolean {
    val emailPattern = "^[a-zA-Z0-9_.±]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+\$"
    return this.matches(emailPattern.toRegex())
}

fun String.isValidPhoneNumber(): Boolean {
    val phoneRegex =
        """^\+?(\d{1,3})?[-.\s]?(\(?\d{1,4}\)?)[-.\s]?(\d{1,4})[-.\s]?(\d{1,9})([-.\s]?\d{1,9})?$"""
    return this.matches(phoneRegex.toRegex())
}