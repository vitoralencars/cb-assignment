package com.coolbluetest.ui.screen.search.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.coolbluetest.R
import com.coolbluetest.ui.screen.search.SearchViewModel
import com.coolbluetest.ui.screen.search.util.SearchException

@Composable
fun SearchError(
    viewModel: SearchViewModel,
    error: SearchException,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = error.throwable.localizedMessage ?: "")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                viewModel.fetchSearchProducts(error.queryError)
            }) {
                Text(text = stringResource(R.string.search_try_again_button))
            }
        }
    }
}
