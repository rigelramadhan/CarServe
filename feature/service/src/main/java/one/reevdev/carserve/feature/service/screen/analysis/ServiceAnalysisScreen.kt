package one.reevdev.carserve.feature.service.screen.analysis

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.service.R
import one.reevdev.carserve.feature.service.component.CardColumn

@Composable
fun ServiceAnalysisScreen(
    modifier: Modifier = Modifier,
    problem: String = "",
    potentialSolve: String = "",
    recommendedAction: String = "",
    estimatedPrice: String = "",
    image: Uri? = null,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFCDEDED)),
            painter = rememberAsyncImagePainter(model = image),
            contentDescription = stringResource(R.string.content_description_analyzed_vehicle_image),
            contentScale = ContentScale.Crop,
        )
        CardColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp, bottom = 20.dp),
            label = stringResource(R.string.label_analysis),
        ) {
            Text(
                modifier = Modifier,
                text = problem,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                modifier = Modifier,
                text = potentialSolve,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        CardColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp),
            label = stringResource(R.string.label_recommendations),
        ) {
            Text(
                modifier = Modifier,
                text = recommendedAction,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            text = estimatedPrice,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Light)
        )
    }
}

@Preview
@Composable
private fun ServiceAnalysisPreview() {
    CarServeTheme {
        ServiceAnalysisScreen(
            problem = "This is the problem of the car",
            potentialSolve = "This is the potential solve of the problem",
            recommendedAction = "This is the recommended action of the problem",
            estimatedPrice = "Rp244,000 - Rp525,000",
        )
    }
}