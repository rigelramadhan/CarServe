package one.reevdev.carserve.ui.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import one.reevdev.carserve.utils.BottomNavBarData

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavBarData>,
    selected: Int,
    onItemSelect: (Int) -> Unit,
) {
    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, navBarData ->
            NavBarItem(
                icon = navBarData.icon,
                label = navBarData.label,
                selected = index == selected,
                onClick = { onItemSelect(index) }
            )
        }
    }
}

@Composable
fun RowScope.NavBarItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    NavigationBarItem(
        modifier = modifier,
        icon = { Icon(icon, null) },
        label = { Text(text = label) },
        selected = selected,
        onClick = onClick
    )
}