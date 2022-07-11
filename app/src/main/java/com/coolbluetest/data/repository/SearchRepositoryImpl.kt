package com.coolbluetest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.coolbluetest.data.source.remote.SearchService
import com.coolbluetest.domain.paging.SearchPagingSource
import com.coolbluetest.domain.repository.SearchRepository
import com.coolbluetest.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val service: SearchService
) : SearchRepository {
    override fun fetchSearchProducts(query: String): Flow<PagingData<Product>> =
        Pager(
            config = PagingConfig(pageSize = PRODUCTS_PAGE_SIZE),
            pagingSourceFactory = {
                SearchPagingSource(service, query)
            }
        ).flow

    companion object {
        private const val PRODUCTS_PAGE_SIZE = 24
    }
}
