package one.reevdev.carserve.feature.profile.screen.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import one.reevdev.carserve.core.domain.feature.profile.model.Customer
import one.reevdev.carserve.feature.common.ui.theme.CarServeTheme
import one.reevdev.carserve.feature.profile.component.CustomerItem

@Composable
fun CustomerListScreen(
    modifier: Modifier = Modifier,
    customers: List<Customer>,
    onClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(customers) { customer ->
            CustomerItem(customer = customer, onClick = onClick)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomerListScreenPreview() {
    CarServeTheme {
        CustomerListScreen(
            customers = listOf(
                Customer(
                    "John Doe",
                    "john@doe.com",
                    "081311111111",
                    "Jl. Address, West Java, Indonesia"
                ),
                Customer(
                    "John Doe",
                    "john@doe.com",
                    "081311111111",
                    "Jl. Address, West Java, Indonesia"
                ),
                Customer(
                    "John Doe",
                    "john@doe.com",
                    "081311111111",
                    "Jl. Address, West Java, Indonesia"
                ),
                Customer(
                    "John Doe",
                    "john@doe.com",
                    "081311111111",
                    "Jl. Address, West Java, Indonesia"
                ),
                Customer(
                    "John Doe",
                    "john@doe.com",
                    "081311111111",
                    "Jl. Address, West Java, Indonesia"
                ),
                Customer(
                    "John Doe",
                    "john@doe.com",
                    "081311111111",
                    "Jl. Address, West Java, Indonesia"
                ),
            )
        ) {}
    }
}