package com.coolbluetest.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun ProductName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        color = Color.Black,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
    )
}

@Composable
fun ProductUSPs(USPs: List<String>, modifier: Modifier = Modifier) {
    Column {
        for (usp in USPs) {
            Text(
                text = "- $usp",
                color = Color.Gray,
                fontSize = 14.sp,
                modifier = modifier
            )
        }
    }
}
