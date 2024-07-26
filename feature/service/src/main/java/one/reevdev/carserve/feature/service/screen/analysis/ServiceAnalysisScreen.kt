package one.reevdev.carserve.feature.service.screen.analysis

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import one.reevdev.carserve.core.common.data.toRupiahCurrency
import one.reevdev.carserve.core.domain.feature.profile.model.SavedProfile
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.vehicle.model.Vehicle
import one.reevdev.carserve.feature.common.ui.component.EmptyComponent
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.component.TextWithLabel
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.component.CardColumn

@Composable
fun ServiceAnalysisScreen(
    modifier: Modifier = Modifier,
    loadingState: LoadingState,
    findings: List<ServiceFinding>? = null,
    vehicle: Vehicle? = null,
    profile: SavedProfile? = null,
    recommendedAction: String,
    estimatedPrice: Double,
    image: Bitmap? = null,
    onProceed: () -> Unit,
    onExportPdf: () -> Unit,
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
                        .clip(RoundedCornerShape(16.dp)),
                    painter = rememberAsyncImagePainter(model = image),
                    contentDescription = stringResource(R.string.content_description_analyzed_vehicle_image),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        analysisDetail(
            loadingState = loadingState,
            vehicle = vehicle,
            profile = profile,
            findings = findings,
            recommendedAction = recommendedAction,
            estimatedPrice = estimatedPrice
        )

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)

            ) {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    onClick = onProceed
                ) {
                    Text(text = stringResource(id = R.string.label_proceed))
                }
                OutlinedButton(
                    modifier = Modifier,
                    onClick = onExportPdf
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Outlined.PictureAsPdf,
                        contentDescription = null
                    )
                    Text(text = stringResource(R.string.action_export_pdf))
                }
            }
        }
    }
}

fun LazyListScope.analysisDetail(
    loadingState: LoadingState,
    vehicle: Vehicle?,
    profile: SavedProfile?,
    findings: List<ServiceFinding>?,
    recommendedAction: String,
    estimatedPrice: Double,
) {
    vehicle?.run {
        item {
            CardColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                label = stringResource(R.string.label_vehicle_information),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
            ) {
                Row {
                    Text(
                        modifier = Modifier
                            .weight(1f),
                        text = carName,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        modifier = Modifier,
                        text = transmission,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    profile?.let {
        item {
            CardColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                label = stringResource(R.string.label_customer_information),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextWithLabel(label = stringResource(R.string.label_name), text = it.name)
                    TextWithLabel(label = stringResource(R.string.label_email), text = it.email)
                    TextWithLabel(label = stringResource(R.string.label_phone_number), text = it.phoneNumber)
                    TextWithLabel(label = stringResource(R.string.label_address), text = it.address)
                }
            }
        }
    }

    findings?.let {
        when (loadingState) {
            is LoadingState.NotLoading -> {
                findingResult(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp),
                    findings = it,
                    scope = this
                )
            }
            is LoadingState.CustomLoading -> {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 24.dp),
                    ) {
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                        loadingState.messageRes?.let { message ->
                            Text(
                                modifier = Modifier.padding(top = 8.dp),
                                text = stringResource(message)
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }

    item {
        if (recommendedAction.isNotBlank()) {
            CardColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
                label = stringResource(R.string.label_recommendations),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                )
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
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp),
            ) {
                HorizontalDivider()
                LabelText(
                    modifier = Modifier.padding(top = 24.dp),
                    label = stringResource(R.string.label_total_estimated_price)
                )
                Text(
                    modifier = Modifier,
                    text = estimatedPrice.toRupiahCurrency(),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

fun findingResult(
    modifier: Modifier = Modifier,
    findings: List<ServiceFinding>,
    scope: LazyListScope,
) {
    scope.run {
        if (findings.isNotEmpty()) {
            item {
                Row(
                    modifier = modifier
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
        } else {
            item {
                EmptyComponent(
                    modifier = modifier,
                    text = stringResource(R.string.text_no_analysis_found)
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
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
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
            loadingState = LoadingState.CustomLoading(message = "Analyzing Car"),
            findings = listOf(
                ServiceFinding("Problem 1", "Solution 1", 24500.0),
                ServiceFinding("Problem 2", "Solution 2", 0.0),
                ServiceFinding("Problem 3", "Solution 3", 624500.0),
            ),
            profile = SavedProfile("John Doe", "john@doe.com", "081311111111", "Jl. Address, West Java, Indonesia"),
            vehicle = Vehicle(1, "Xenia", "Blue", "MT"),
            recommendedAction = "This is the recommended action of the problem",
            estimatedPrice = 244000.0,
            onProceed = {}
        ) {

        }
    }
}