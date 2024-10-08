package one.reevdev.carserve.feature.common.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import one.reevdev.carserve.core.common.data.emptyString
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun ConfirmationDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String? = null,
    negativeButtonText: String = emptyString(),
    onNegativeAction: (() -> Unit)? = null,
    positiveButtonText: String,
    onPositiveAction: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                message?.let {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        text = it,
                        textAlign = TextAlign.Center
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 32.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    onNegativeAction?.let {
                        OutlinedButton(
                            modifier = Modifier
                                .weight(1f),
                            onClick = onNegativeAction,
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.error)
                        ) {
                            Text(
                                color = MaterialTheme.colorScheme.error,
                                text = negativeButtonText
                            )
                        }
                    }
                    Button(
                        modifier = Modifier
                            .weight(1f),
                        onClick = onPositiveAction
                    ) {
                        Text(text = positiveButtonText)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfirmationDialogPreview() {
    CarServeTheme {
        ConfirmationDialog(
            title = "Confirmation",
            message = "What do you want to do with this car?",
            positiveButtonText = "Analyze",
            negativeButtonText = "Delete",
            onPositiveAction = {

            },
            onNegativeAction = {

            }
        ) {}
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ConfirmationDialogPreview_Night() {
    CarServeTheme {
        ConfirmationDialog(
            title = "What to do with this car?",
            message = "What do you want to do with this car?",
            positiveButtonText = "Analyze",
            negativeButtonText = "Delete",
            onPositiveAction = {

            },
            onNegativeAction = {

            }
        ) {}
    }
}