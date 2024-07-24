package one.reevdev.carserve.feature.service.screen.symptom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.screen.ServiceAnalysisViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SymptomFormRouter(
    modifier: Modifier = Modifier,
    viewModel: ServiceAnalysisViewModel = hiltViewModel(),
    proceedToAnalysis: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.label_symptoms_problems)) })
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