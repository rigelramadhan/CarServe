package one.reevdev.carserve.feature.service.screen.analysis

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import one.reevdev.carserve.core.common.data.toRupiahCurrency
import one.reevdev.carserve.core.domain.model.service.ServiceFinding
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.component.CardColumn

@Composable
fun ServiceAnalysisScreen(
    modifier: Modifier = Modifier,
    findings: List<ServiceFinding> = emptyList(),
    recommendedAction: String,
    estimatedPrice: Double,
    image: Bitmap? = null,
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        image?.let { image ->
            item {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(Color(0xFFCDEDED)),
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = stringResource(R.string.content_description_analyzed_vehicle_image),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                LabelText(label = stringResource(id = R.string.label_analysis))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.format_analysis_found, findings.size),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        items(findings) { finding ->
            FindingComponent(
                problem = finding.problem,
                potentialSolve = finding.solution,
                estimatedPrice = finding.estimatedPrice,
            )
        }

        item {
            if (recommendedAction.isNotBlank()) {
                CardColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp),
                    label = stringResource(R.string.label_recommendations),
                ) {
                    Text(
                        modifier = Modifier,
                        text = recommendedAction,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        if (estimatedPrice > 0.0) {
            item {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(vertical = 24.dp),
                    text = estimatedPrice.toRupiahCurrency(),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light)
                )
            }
        }
    }
}

@Composable
fun FindingComponent(
    modifier: Modifier = Modifier,
    problem: String,
    potentialSolve: String,
    estimatedPrice: Double,
) {
    CardColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 4.dp),
    ) {
        Text(
            modifier = Modifier,
            text = problem,
            style = MaterialTheme.typography.bodyLarge
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.label_solution),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )
        Text(
            modifier = Modifier,
            text = potentialSolve,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        val finalPrice =
            if (estimatedPrice <= 0.0) "No cost"
            else stringResource(
                R.string.format_estimated_price,
                estimatedPrice.toRupiahCurrency()
            )
        Text(
            modifier = Modifier,
            text = finalPrice,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Light)
        )
    }
}

@Preview
@Composable
private fun ServiceAnalysisPreview() {
    CarServeTheme {
        ServiceAnalysisScreen(
            findings = listOf(
                ServiceFinding("Problem 1", "Solution 1", 24500.0),
                ServiceFinding("Problem 2", "Solution 2", 0.0),
                ServiceFinding("Problem 3", "Solution 3", 624500.0),
            ),
            recommendedAction = "This is the recommended action of the problem",
            estimatedPrice = 244.000,
        )
    }
}