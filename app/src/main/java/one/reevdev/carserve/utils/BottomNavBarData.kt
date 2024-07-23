package one.reevdev.carserve.utils

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BottomNavBarData(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    val navigateAction: () -> Unit,
)
