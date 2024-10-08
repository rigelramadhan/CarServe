package one.reevdev.carserve.feature.service.screen.analysis

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.PersonPinCircle
import androidx.compose.material.icons.outlined.PictureAsPdf
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import one.reevdev.carserve.core.common.data.toRupiahCurrency
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.core.domain.feature.service.model.ServiceFinding
import one.reevdev.carserve.core.domain.feature.vehicle.model.CustomerVehicle
import one.reevdev.carserve.feature.common.ui.component.EmptyComponent
import one.reevdev.carserve.feature.common.ui.component.LabelText
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.component.AnalysisFindingItem
import one.reevdev.carserve.feature.service.component.CardColumn

@Composable
fun ServiceAnalysisScreen(
    modifier: Modifier = Modifier,
    loadingState: LoadingState,
    findings: List<ServiceFinding> = emptyList(),
    vehicle: CustomerVehicle = CustomerVehicle(),
    customer: Customer = Customer(),
    recommendedAction: String,
    estimatedPrice: Double,
    image: Bitmap? = null,
    onPhoneClick: (String) -> Unit,
    onBookService: () -> Unit,
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
            customer = customer,
            findings = findings,
            recommendedAction = recommendedAction,
            estimatedPrice = estimatedPrice,
            onPhoneClick = onPhoneClick
        )

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    OutlinedButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        onClick = onBookService
                    ) {
                        Text(text = stringResource(id = R.string.label_book_service))
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
                Button(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    onClick = onProceed
                ) {
                    Text(text = stringResource(id = R.string.label_proceed))
                }
            }
        }
    }
}

fun LazyListScope.analysisDetail(
    loadingState: LoadingState,
    vehicle: CustomerVehicle,
    customer: Customer,
    findings: List<ServiceFinding>,
    recommendedAction: String,
    estimatedPrice: Double,
    onPhoneClick: (String) -> Unit,
) {
    item {
        if (vehicle.carType.isNotBlank()) {
            OutlinedCard(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 24.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        modifier = Modifier,
                        text = "${vehicle.carBrand} ${vehicle.carName}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        modifier = Modifier,
                        text = stringResource(
                            R.string.format_policeno_color_transmission,
                            vehicle.policeNo,
                            vehicle.color,
                            vehicle.transmission
                        ),
                        style = MaterialTheme.typography.bodySmall
                    )
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    if (customer.email.isNotBlank()) {
                        Row {
                            Image(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape),
                                painter = painterResource(id = one.reevdev.carserve.feature.common.R.drawable.placeholder_customer_avatar),
                                contentDescription = null,
                            )
                            Column(
                                Modifier
                                    .padding(start = 16.dp)
                                    .weight(1f)
                            ) {
                                Text(
                                    modifier = Modifier,
                                    text = customer.name,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    modifier = Modifier,
                                    text = customer.phoneNumber,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.5f
                                        )
                                    )
                                )
                                Text(
                                    modifier = Modifier,
                                    text = customer.email,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.onSurface.copy(
                                            alpha = 0.5f
                                        )
                                    )
                                )
                            }
                            IconButton(
                                onClick = { onPhoneClick(customer.phoneNumber) },
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Call,
                                    contentDescription = null
                                )
                            }
                        }
                        Card(
                            modifier = Modifier
                                .padding(top = 16.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        ) {
                            Row(modifier = Modifier.padding(8.dp)) {
                                Icon(
                                    modifier = Modifier.size(18.dp),
                                    imageVector = Icons.Outlined.PersonPinCircle,
                                    contentDescription = null
                                )
                                Text(
                                    modifier = Modifier
                                        .padding(start = 4.dp)
                                        .fillMaxWidth(),
                                    text = customer.address,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    findings.let {
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
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
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
                AnalysisFindingItem(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp),
                    solution = finding.solution,
                    analysis = finding.problem,
                    estimatedPrice = finding.estimatedPrice.toRupiahCurrency(),
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

@Preview
@Composable
private fun ServiceAnalysisPreview() {
    CarServeTheme {
        ServiceAnalysisScreen(
            loadingState = LoadingState.NotLoading,
            findings = listOf(
                ServiceFinding("Problem 1", "Solution 1", 24500.0),
                ServiceFinding("Problem 2", "Solution 2", 0.0),
                ServiceFinding("Problem 3", "Solution 3", 624500.0),
            ),
            customer = Customer(
                "John Doe",
                "john@doe.com",
                "081311111111",
                "Jl. Address, West Java, Indonesia"
            ),
            vehicle = CustomerVehicle(
                policeNo = "AG 2446 NB",
                ownerEmail = "john@doe.com",
                carBrand = "Brand 1",
                carName = "Car Name 1",
                color = "Color 1",
                carType = "Car Type 1",
                transmission = "Transmission"
            ),
            recommendedAction = "This is the recommended action of the problem",
            estimatedPrice = 244000.0,
            onPhoneClick = {},
            onBookService = {},
            onProceed = {}
        ) {

        }
    }
}

@Preview
@Composable
private fun ServiceAnalysisPreview_Empty() {
    CarServeTheme {
        ServiceAnalysisScreen(
            loadingState = LoadingState.NotLoading,
            findings = emptyList(),
            customer = Customer(
                "John Doe",
                "john@doe.com",
                "081311111111",
                "Jl. Address, West Java, Indonesia"
            ),
            vehicle = CustomerVehicle(
                policeNo = "AG 2446 NB",
                ownerEmail = "john@doe.com",
                carBrand = "Brand 1",
                carName = "Car Name 1",
                color = "Color 1",
                carType = "Car Type 1",
                transmission = "Transmission"
            ),
            recommendedAction = "This is the recommended action of the problem",
            estimatedPrice = 244000.0,
            onPhoneClick = {},
            onBookService = {},
            onProceed = {}
        ) {

        }
    }
}