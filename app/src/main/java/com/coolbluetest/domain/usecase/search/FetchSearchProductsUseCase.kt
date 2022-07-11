package com.coolbluetest.domain.usecase.search

import com.coolbluetest.domain.repository.SearchRepository
import javax.inject.Inject

class FetchSearchProductsUseCase @Inject constructor(private val repository: SearchRepository) {
    operator fun invoke(query: String) = repository.fetchSearchProducts(query)
}
