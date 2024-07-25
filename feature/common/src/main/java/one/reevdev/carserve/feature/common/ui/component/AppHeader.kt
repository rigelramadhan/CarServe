package one.reevdev.carserve.feature.common.ui.component

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.R
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    title: String? = null,
    hasBackButton: Boolean = false,
) {
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            if (title != null) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    text = title,
                    style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
                    textAlign = TextAlign.Center
                )
            } else {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .height(28.dp),
                    painter = painterResource(id = R.drawable.ic_carse_logo),
                    contentDescription = stringResource(R.string.content_description_app_logo),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        navigationIcon = {
            if (hasBackButton) {
                IconButton(onClick = { backPressedDispatcher?.onBackPressed() }) {
                    Icon(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        painter = painterResource(id = R.drawable.ic_arrow_back_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
    )
}

@Preview
@Composable
private fun AppHeaderPreview() {
    CarServeTheme {
        AppHeader()
    }
}

@Preview
@Composable
private fun AppHeaderPreview_Title() {
    CarServeTheme {
        AppHeader(
            title = "Vehicle",
            hasBackButton = true
        )
    }
}