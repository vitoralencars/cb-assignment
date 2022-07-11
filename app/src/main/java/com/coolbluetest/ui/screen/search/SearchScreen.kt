@file:OptIn(ExperimentalComposeUiApi::class)

package com.coolbluetest.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.coolbluetest.R
import com.coolbluetest.ui.common.*
import com.coolbluetest.ui.screen.search.util.SearchException
import com.coolbluetest.ui.screen.search.widget.ProductsList
import com.coolbluetest.ui.screen.search.widget.SearchBar
import com.coolbluetest.ui.screen.search.widget.SearchError

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        SearchBar(viewModel)
        ScreenContent(viewModel)
    }
}

@Composable
fun ScreenContent(
    viewModel: SearchViewModel
) {
    if (viewModel.isDefaultScreenState.collectAsState().value) {
        CenteredText(text = stringResource(R.string.search_greetings))
    } else {
        val products = viewModel.productsState.collectAsLazyPagingItems()

        when (val loadState = products.loadState.source.refresh) {
            is LoadState.Loading -> LoadingProgressIndicator()
            is LoadState.Error -> if (loadState.error is SearchException) {
                SearchError(
                    viewModel = viewModel,
                    error = loadState.error as SearchException
                )
            }
            is LoadState.NotLoading -> if (products.itemCount > 0) {
                ProductsList(products)
            } else {
                CenteredText(text = stringResource(R.string.search_empty_list))
            }
        }
    }
}
