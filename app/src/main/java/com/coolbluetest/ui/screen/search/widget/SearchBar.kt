package com.coolbluetest.ui.screen.search.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.coolbluetest.R
import com.coolbluetest.ui.screen.search.SearchViewModel

@ExperimentalComposeUiApi
@Composable
fun SearchBar(viewModel: SearchViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White)
            .padding(start = 8.dp, end = 8.dp)

    ) {
        var queryText by remember { mutableStateOf("") }
        var lastQueryText = ""
        val keyboardController = LocalSoftwareKeyboardController.current

        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_bar_hint),
            tint = Color.Gray,
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = queryText,
            onValueChange = { queryText = it },
            label = { Text(text = stringResource(R.string.search_bar_hint)) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    if (queryText != lastQueryText) {
                        lastQueryText = queryText
                        viewModel.fetchSearchProducts(queryText)
                    }
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.background(color = Color.Transparent)
        )
    }
}
