package one.reevdev.carserve.feature.service.screen.detail

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.service.model.ServiceAnalysis
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.common.ui.state.LoadingState
import one.reevdev.carserve.feature.common.utils.dialNumber
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.utils.DocumentHelper

@Composable
fun AnalysisDetailRouter(
    modifier: Modifier = Modifier,
    serviceAnalysis: ServiceAnalysis,
    context: Context = LocalContext.current,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            AppHeader(
                title = stringResource(R.string.label_analysis),
                hasBackButton = true,
                actions = {
                    IconButton(
                        onClick = {
                            DocumentHelper.printPdf(
                                analysisResultHtml = serviceAnalysis.analysisHtml,
                                context = context,
                                onViewerUnavailable = {}
                            )
                        },
                        content = {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                imageVector = Icons.Rounded.Download,
                                contentDescription = null
                            )
                        }
                    )
                })
        }
    ) { innerPadding ->
        with(serviceAnalysis) {
            AnalysisDetailScreen(
                modifier = Modifier
                    .padding(innerPadding),
                loadingState = LoadingState.NotLoading,
                vehicle = vehicle,
                profile = profile,
                findings = serviceFindings,
                recommendedAction = recommendedAction,
                estimatedPrice = totalEstimatedPrice,
                onPhoneClick = { it.dialNumber(context) }
            )
        }
    }
}