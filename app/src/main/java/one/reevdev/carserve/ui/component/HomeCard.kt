package one.reevdev.carserve.ui.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.R
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme

@Composable
fun HomeCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    @DrawableRes drawable: Int,
    onClick: () -> Unit,
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Box(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = drawable),
                    contentDescription = stringResource(R.string.content_description_analysis_card)
                )
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModeCardMPPreview() {
    CarServeTheme {
        HomeCard(
            modifier = Modifier
                .padding(16.dp),
            title = "Check Your Vehicle!",
            description = "Check your vehicle by capturing a picture of where you thing the problem is.",
            drawable = R.drawable.car_analysis_illustration
        ) {

        }
    }
}