package one.reevdev.carserve.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import one.reevdev.carserve.utils.BottomNavBarData

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavBarData>,
    currentRoute: String,
    onItemSelect: (Any) -> Unit,
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEach { navBarData ->
            NavBarItem(
                icon = painterResource(navBarData.icon),
                iconTint = navBarData.iconTint ?: MaterialTheme.colorScheme.onSurface,
                label = stringResource(navBarData.label),
                selected = navBarData.route.toString() == currentRoute.substringAfterLast("."),
                onClick = { onItemSelect(navBarData.route) }
            )
        }
    }
}

@Composable
fun RowScope.NavBarItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    iconTint: Color = Color.Unspecified,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        modifier = modifier,
        icon = {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = iconTint
            )
        },
        label = { Text(text = label, textAlign = TextAlign.Center) },
        selected = selected,
        onClick = {
            onClick()
        }
    )
}