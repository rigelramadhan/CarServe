package one.reevdev.carserve.feature.service.screen.symptom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel

@Composable
fun SymptomFormRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    proceedToAnalysis: () -> Unit,
) {
    SymptomFormScreen(
        modifier = modifier
    ) { symptoms, complaints ->
        viewModel.apply {
            setSymptoms(symptoms)
            setGeneralProblem(complaints)
        }
        proceedToAnalysis()
    }
}