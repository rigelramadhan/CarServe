package one.reevdev.carserve.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color

data class BottomNavBarData(
    val route: Any,
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val iconTint: Color? = null,
)
