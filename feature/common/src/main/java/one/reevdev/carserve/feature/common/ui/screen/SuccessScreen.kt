package one.reevdev.carserve.feature.common.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.R
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    value: String,
    onProceed: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.success_illustration),
                    contentDescription = null
                )
                Text(modifier = Modifier.padding(top = 32.dp), text = value)
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
            ,
            onClick = onProceed
        ) {
            Text(text = stringResource(R.string.action_proceed))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SuccessScreenPreview() {
    CarServeTheme {
        SuccessScreen(value = "Service has been booked successfully!") {}
    }
}