package one.reevdev.carserve.feature.service.screen.symptom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R

@Composable
fun SymptomFormScreen(
    modifier: Modifier = Modifier,
    onProceedForm: (symptoms: String, complaints: String) -> Unit,
) {
    var symptoms by rememberSaveable { mutableStateOf(emptyString()) }
    var generalProblem by rememberSaveable { mutableStateOf(emptyString()) }

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp,bottom = 16.dp),
            value = symptoms,
            onValueChange = { symptoms = it },
            label = { Text(stringResource(R.string.label_symptoms)) },
            shape = RoundedCornerShape(16.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            value = generalProblem,
            onValueChange = { generalProblem = it },
            label = { Text(stringResource(R.string.label_other_complaints)) },
            shape = RoundedCornerShape(16.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onProceedForm(symptoms, generalProblem) }
        ) {
            Text(text = stringResource(R.string.label_proceed))
        }
    }
}

@Preview
@Composable
private fun SymptomFormScreenPreview() {
    CarServeTheme {
        SymptomFormScreen { _, _ ->

        }
    }
}