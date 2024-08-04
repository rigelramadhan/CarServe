package one.reevdev.carserve.feature.service.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R

@Composable
fun AnalysisFindingItem(
    modifier: Modifier = Modifier,
    solution: String,
    estimatedPrice: String,
    analysis: String,
) {
    OutlinedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = solution,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = estimatedPrice,
                style = MaterialTheme.typography.bodySmall
            )
            LabelText(
                modifier = Modifier
                    .padding(top = 16.dp),
                label = stringResource(id = R.string.label_analysis),
                style = MaterialTheme.typography.bodySmall
            )
            Card(
                modifier = Modifier
                    .padding(top = 4.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    text = analysis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun AnalysisFindingItemPreview() {
    CarServeTheme {
        AnalysisFindingItem(
            solution = "Battery Replacement",
            estimatedPrice = "Rp600,000.00",
            analysis = "The car is not starting, which could be due to a variety of issues, such as a dead battery, faulty starter, or fuel system problems."
        )
    }
}