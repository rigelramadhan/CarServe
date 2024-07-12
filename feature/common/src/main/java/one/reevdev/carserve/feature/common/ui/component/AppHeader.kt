package one.reevdev.carserve.feature.common.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.R

@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    title: String = stringResource(R.string.app_name)
) {
    Text(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth(),
        text = title,
        style = MaterialTheme.typography.headlineMedium.copy(color = MaterialTheme.colorScheme.primary),
        textAlign = TextAlign.Center
    )
}