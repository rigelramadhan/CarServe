package one.reevdev.carserve.feature.service.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.component.LabelText

@Composable
fun CardColumn(
    modifier: Modifier = Modifier,
    padding: Dp = 16.dp,
    colors: CardColors = CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
        contentColor = MaterialTheme.colorScheme.onSurface
    ),
    label: String? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        label?.let {
            LabelText(label = it)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = colors
        ) {
            Column(
                modifier = Modifier
                    .padding(padding)
            ) {
                content()
            }
        }
    }
}