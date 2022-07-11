package com.coolbluetest.ui.screen.search.util

data class SearchException(
    val queryError: String,
    val throwable: Throwable
) : Exception()
