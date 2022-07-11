package com.coolbluetest.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.coolbluetest.ui.theme.Green850
import com.coolbluetest.util.formatCurrency

@Composable
fun SalesPrice(price: Double, modifier: Modifier = Modifier) {
    Text(
        text = price.formatCurrency(),
        color = Green850,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = modifier
    )
}

@Composable
fun RetailPrice(price: Double, modifier: Modifier = Modifier) {
    Text(
        text = price.formatCurrency(),
        color = Color.Gray,
        style = TextStyle(textDecoration = TextDecoration.LineThrough),
        modifier = modifier
    )
}
