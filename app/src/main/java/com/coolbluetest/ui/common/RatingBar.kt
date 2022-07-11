package com.coolbluetest.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coolbluetest.R

@Composable
fun RatingBar(ratingValue: Int, ratingCount: Int = -1) {
    Row {
        LazyRow {
            itemsIndexed(arrayOfNulls<Any>(5)) { index, _ ->
                Image(
                    painterResource(if (index + 1 <= ratingValue) {
                        R.drawable.ic_star_fill_rating
                    }else {
                        R.drawable.ic_star_outline_rating
                    }),
                    contentDescription = "rating star"
                )
            }
        }
        if (ratingCount > -1) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "(${ratingCount})",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
