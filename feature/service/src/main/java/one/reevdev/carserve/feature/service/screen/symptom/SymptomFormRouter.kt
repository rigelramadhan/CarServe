package one.reevdev.carserve.feature.service.screen.symptom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.feature.common.ui.component.AppHeader
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel

@Composable
fun SymptomFormRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    proceedToAnalysis: () -> Unit,
) {
    Scaffold(
        topBar = {
            AppHeader(
                title = stringResource(R.string.label_symptoms_problems),
                hasBackButton = true,
            )
        }
    ) { innerPadding ->
        SymptomFormScreen(
            modifier = modifier
                .padding(innerPadding)
        ) { symptoms, complaints ->
            viewModel.apply {
                setSymptoms(symptoms)
                setGeneralProblem(complaints)
            }
            proceedToAnalysis()
        }
    }
}