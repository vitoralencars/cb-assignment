package com.coolbluetest.domain.usecase.search

import javax.inject.Inject

data class SearchUseCases @Inject constructor(
    val fetchSearchProducts: FetchSearchProductsUseCase
)
