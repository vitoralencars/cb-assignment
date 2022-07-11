@file:OptIn(ExperimentalMaterial3Api::class)

package com.coolbluetest.ui.screen.search.widget

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.coolbluetest.R
import com.coolbluetest.domain.model.Product
import com.coolbluetest.ui.common.*
import kotlin.math.roundToInt

@Composable
fun ProductsList(products: LazyPagingItems<Product>) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = products
        ) { product ->
            product?.let {
                ProductItem(product = it)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    val context = LocalContext.current
    val tappedMessage = stringResource(R.string.search_item_tapped, product.productName)
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            Toast.makeText(
                context,
                tappedMessage,
                Toast.LENGTH_SHORT
            ).show()
        }
    ) {
        product.run {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp),
            ) {
                AsyncImage(
                    model = productImage,
                    contentDescription = stringResource(
                        R.string.search_image_content_description,
                        productName
                    )
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    ProductName(name = productName)
                    Spacer(modifier = Modifier.height(12.dp))
                    usps?.let {
                        ProductUSPs(USPs = it)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    reviewInformation.reviewSummary.run {
                        RatingBar(
                            ratingValue = (reviewAverage.roundToInt()/2),
                            ratingCount = reviewCount
                        )
                    }

                    if (isNextDayDelivery) {
                        Spacer(modifier = Modifier.height(12.dp))
                        NextDayDeliveryBanner()
                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    SalesPrice(
                        price = salesPrice,
                        modifier = Modifier.align(Alignment.End)
                    )
                    retailPrice?.let {
                        RetailPrice(
                            price = it,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
        }
    }
}
