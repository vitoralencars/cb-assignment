package com.coolbluetest.common

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.coolbluetest.domain.model.Product
import com.coolbluetest.domain.model.ReviewInformation
import com.coolbluetest.domain.model.ReviewSummary
import com.coolbluetest.domain.model.SearchResults
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

object MockedData {

    fun getProductsFlowPagingData() = flow {
        emit(PagingData.from(getMockedProducts()))
    }

    fun getMockedResults(totalResults: Int = getMockedProducts().size + 1) = SearchResults(
        products = getMockedProducts(),
        currentPage = 0,
        pageSize = 0,
        totalResults = totalResults,
        pageCount = 0
    )

    fun getMockedProducts() = listOf(
        Product(
            productId = 1,
            productName = "product 1",
            usps = listOf("1", "2"),
            reviewInformation = ReviewInformation(
                reviewSummary = ReviewSummary(reviewAverage = 9.2, reviewCount = 200)
            ),
            availabilityState = 1,
            salesPrice = 100.0,
            retailPrice = 150.0,
            productImage = "image 1",
            isNextDayDelivery = false
        ),
        Product(
            productId = 2,
            productName = "product 2",
            usps = listOf("3", "4"),
            reviewInformation = ReviewInformation(
                reviewSummary = ReviewSummary(reviewAverage = 9.8, reviewCount = 350)
            ),
            availabilityState = 1,
            salesPrice = 200.0,
            retailPrice = 250.0,
            productImage = "image 2",
            isNextDayDelivery = true
        ),
    )

    fun getLoadParams() = PagingSource.LoadParams.Refresh(
        key = 2,
        loadSize = 2,
        placeholdersEnabled = false
    )
}
