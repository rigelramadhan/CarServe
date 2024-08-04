package one.reevdev.carserve.feature.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import one.reevdev.carserve.core.domain.feature.profile.model.Customer

@Composable
fun CustomerItem(
    modifier: Modifier = Modifier,
    customer: Customer,
    onClick: (String) -> Unit
) {
    Row(
        modifier = modifier
            .padding(16.dp)
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(id = one.reevdev.carserve.feature.common.R.drawable.placeholder_customer_avatar),
            contentDescription = null,
        )
        Column(
            Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                modifier = Modifier,
                text = customer.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                modifier = Modifier,
                text = customer.phoneNumber,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            )
            Text(
                modifier = Modifier,
                text = customer.email,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            )
        }
        IconButton(
            onClick = { onClick(customer.phoneNumber) },
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        ) {
            Icon(
                imageVector = Icons.Outlined.Call,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
private fun CustomerItemPreview() {
    
}