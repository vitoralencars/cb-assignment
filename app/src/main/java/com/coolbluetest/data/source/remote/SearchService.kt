package com.coolbluetest.data.source.remote

import com.coolbluetest.domain.model.SearchResults
import com.coolbluetest.data.source.remote.ServiceConstants.Search.PAGE_PATH
import com.coolbluetest.data.source.remote.ServiceConstants.Search.QUERY_PATH
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search")
    suspend fun fetchSearchProducts(
        @Query(QUERY_PATH) query: String,
        @Query(PAGE_PATH) page: Int
    ): SearchResults
}
