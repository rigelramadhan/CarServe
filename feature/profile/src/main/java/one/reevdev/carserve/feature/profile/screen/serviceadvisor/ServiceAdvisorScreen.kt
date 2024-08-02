package one.reevdev.carserve.feature.profile.screen.serviceadvisor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.feature.common.ui.component.TextWithLabel
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun ServiceAdvisorScreen(
    modifier: Modifier = Modifier,
    username: String,
    email: String
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextWithLabel(label = "Username", text = username)
        TextWithLabel(label = "Email", text = email)
    }
}

@Preview(showBackground = true)
@Composable
private fun ServiceAdvisorScreenPreview() {
    CarServeTheme {
        ServiceAdvisorScreen(username = "ServiceAdvisor1", email = "test@carserve.com")
    }
}