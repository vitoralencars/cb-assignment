package com.coolbluetest.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.coolbluetest.domain.model.Product
import com.coolbluetest.domain.usecase.search.SearchUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases
) : ViewModel() {

    private val _isDefaultScreenState = MutableStateFlow(true)
    val isDefaultScreenState = _isDefaultScreenState.asStateFlow()

    private val _productsState = MutableStateFlow<PagingData<Product>>(PagingData.empty())
    val productsState = _productsState.asStateFlow()

    fun fetchSearchProducts(query: String) {
        viewModelScope.launch {
            _isDefaultScreenState.emit(false)

            searchUseCases.fetchSearchProducts(query)
                .cachedIn(this)
                .collect {
                    _productsState.emit(it)
                }
        }
    }
}
