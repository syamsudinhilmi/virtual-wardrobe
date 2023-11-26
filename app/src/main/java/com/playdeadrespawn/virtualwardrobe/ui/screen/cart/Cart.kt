package com.playdeadrespawn.virtualwardrobe.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playdeadrespawn.virtualwardrobe.R
import com.playdeadrespawn.virtualwardrobe.injection.Injection
import com.playdeadrespawn.virtualwardrobe.ui.ViewModelFactory
import com.playdeadrespawn.virtualwardrobe.ui.common.UiState
import com.playdeadrespawn.virtualwardrobe.ui.components.ItemCart
import com.playdeadrespawn.virtualwardrobe.ui.components.OrderButton
import com.playdeadrespawn.virtualwardrobe.ui.theme.VirtualWardrobeTheme

@Composable
fun CartScreen(
    onOrderButtonClicked: (String) -> Unit,
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrder()
            }
            is UiState.Success -> {
                CartContent(
                    uiState.data,
                    onProductCountChanged = { wardrobeId, count ->
                        viewModel.updateOrder(wardrobeId, count)
                    },
                    onOrderButtonClicked = onOrderButtonClicked
                )
            }
            is UiState.Error -> {}
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    state: StateCart,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.cart),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(state.order, key = {it.wardrobe.id}) {
                ItemCart(
                    id = it.wardrobe.id,
                    image = it.wardrobe.image,
                    name = it.wardrobe.name,
                    price = it.wardrobe.price,
                    count = it.count,
                    onProductCountChanged = onProductCountChanged
                )
                Divider()
            }

        }
        OrderButton(
            text = "Total: ${state.totalPrice}",
            enabled = state.order.isNotEmpty(),
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun CartContentPreview() {
    VirtualWardrobeTheme {
        CartScreen(
            onOrderButtonClicked = {},
        )
    }
}

