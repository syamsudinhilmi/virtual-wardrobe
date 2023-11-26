package com.playdeadrespawn.virtualwardrobe.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.playdeadrespawn.virtualwardrobe.injection.Injection
import com.playdeadrespawn.virtualwardrobe.model.Order
import com.playdeadrespawn.virtualwardrobe.ui.ViewModelFactory
import com.playdeadrespawn.virtualwardrobe.ui.common.UiState
import com.playdeadrespawn.virtualwardrobe.ui.components.WardrobeItem
import com.playdeadrespawn.virtualwardrobe.ui.theme.VirtualWardrobeTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel( factory = ViewModelFactory(Injection.provideRepository()) ),
    navigateToDetail: (Long) -> Unit
) {
    val query = viewModel.query.value
    SearchBar(
        query = query,
        onQueryChange = viewModel::search,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
    )
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let {
        when (it){
            is UiState.Loading -> {
                viewModel.getAllFashion()
            }
            is UiState.Success -> {
                HomeContent(
                    order = it.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    order: List<Order>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
        ){
        items(order){
            WardrobeItem(
                image = it.wardrobe.image,
                name = it.wardrobe.name,
                price = it.wardrobe.price,
                modifier = Modifier
                    .clickable { navigateToDetail(it.wardrobe.id) }
                    .padding(top = 90.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    androidx.compose.material3.SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},

        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Search")
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {

    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    VirtualWardrobeTheme {
        HomeScreen(
            navigateToDetail = {}
        )
    }
}