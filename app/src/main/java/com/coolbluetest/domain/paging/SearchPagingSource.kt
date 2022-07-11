package com.coolbluetest.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.coolbluetest.data.source.remote.SearchService
import com.coolbluetest.domain.model.Product
import com.coolbluetest.ui.screen.search.util.SearchException

class SearchPagingSource(
    private val service: SearchService,
    private val query: String
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = state.anchorPosition?.let {
        state.closestPageToPosition(it)?.prevKey?.plus(1)
            ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val page = params.key ?: STARTING_PAGE
        checkQuery()

        return try {
            val response = service.fetchSearchProducts(query, page)
            val searchProducts = response.products

            loadedProducts += searchProducts.size
            totalResults = response.totalResults
            val shouldLoadMoreProducts = loadedProducts < totalResults

            LoadResult.Page(
                data = searchProducts,
                prevKey = if (page == STARTING_PAGE) {
                    null
                } else {
                    page.minus(1)
                },
                nextKey = if (shouldLoadMoreProducts) {
                    page.plus(1)
                } else {
                    null
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(SearchException(
                queryError = query,
                throwable = e
            ))
        }
    }

    private fun checkQuery() {
        if (query != currentQuery) {
            changeCurrentQuery()
        }
    }

    private fun changeCurrentQuery() {
        currentQuery = query
        totalResults = TOTAL_RESULTS_INITIAL_STATE
        loadedProducts = LOADED_PRODUCTS_INITIAL_STATE
    }

    companion object {
        private const val STARTING_PAGE = 1
        private const val CURRENT_QUERY_INITIAL_STATE = ""
        private const val TOTAL_RESULTS_INITIAL_STATE = 0
        private const val LOADED_PRODUCTS_INITIAL_STATE = 0

        private var currentQuery = CURRENT_QUERY_INITIAL_STATE
        private var totalResults = TOTAL_RESULTS_INITIAL_STATE
        private var loadedProducts = LOADED_PRODUCTS_INITIAL_STATE
    }
}
