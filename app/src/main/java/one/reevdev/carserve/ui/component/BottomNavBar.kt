package one.reevdev.carserve.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
        items.forEachIndexed { index, navBarData ->
            NavBarItem(
                icon = painterResource(navBarData.icon),
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
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        modifier = modifier,
        icon = { Icon(icon, null) },
        label = { Text(text = label) },
        selected = selected,
        onClick = {
            onClick()
        }
    )
}