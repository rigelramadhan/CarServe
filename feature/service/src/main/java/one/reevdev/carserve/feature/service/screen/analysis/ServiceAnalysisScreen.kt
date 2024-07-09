package one.reevdev.carserve.feature.service.screen.analysis

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.component.CardColumn

@Composable
fun ServiceAnalysisScreen(
    modifier: Modifier = Modifier,
    findings: List<ServiceFinding> = emptyList(),
    recommendedAction: String,
    estimatedPrice: Double,
    image: Uri? = null,
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
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

        item {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.label_analysis),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
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

        item {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp),
                text = estimatedPrice.toRupiahCurrency(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light)
            )
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
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier,
            text = potentialSolve,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier,
            text = estimatedPrice.toRupiahCurrency(),
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
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
                ServiceFinding("Problem 2", "Solution 2", 124500.0),
                ServiceFinding("Problem 3", "Solution 3", 624500.0),
            ),
            recommendedAction = "This is the recommended action of the problem",
            estimatedPrice = 244.000,
        )
    }
}