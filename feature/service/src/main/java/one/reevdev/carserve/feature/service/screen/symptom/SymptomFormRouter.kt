package one.reevdev.carserve.feature.service.screen.symptom

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel

@Composable
fun SymptomFormRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    proceedToAnalysis: () -> Unit,
) {

    LaunchedEffect(true) {
        delay(200)
        viewModel.setLoading(false)
    }

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