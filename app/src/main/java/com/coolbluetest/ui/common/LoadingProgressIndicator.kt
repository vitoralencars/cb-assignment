package com.coolbluetest.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingProgressIndicator(alignment: Alignment = Alignment.Center) {
    Box(
        contentAlignment = alignment,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}
