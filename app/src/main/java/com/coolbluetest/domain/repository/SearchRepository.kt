package com.coolbluetest.domain.repository

import androidx.paging.PagingData
import com.coolbluetest.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun fetchSearchProducts(query: String): Flow<PagingData<Product>>
}
