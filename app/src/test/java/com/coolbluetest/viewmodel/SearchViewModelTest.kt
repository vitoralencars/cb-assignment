package com.coolbluetest.viewmodel

import androidx.paging.PagingData
import com.coolbluetest.common.BaseTest
import com.coolbluetest.common.MockedData
import com.coolbluetest.common.collectData
import com.coolbluetest.domain.model.Product
import com.coolbluetest.domain.usecase.search.SearchUseCases
import com.coolbluetest.ui.screen.search.SearchViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class SearchViewModelTest : BaseTest() {

    private val searchUseCases: SearchUseCases = mockk()
    private val viewModel = SearchViewModel(searchUseCases)

    @Test
    fun fetchSearchProducts() = runTest {
        every { searchUseCases.fetchSearchProducts(any()) } returns
            MockedData.getProductsFlowPagingData()

        val screenStates = mutableListOf<Boolean>()
        val productsStates = mutableListOf<PagingData<Product>>()

        val screenJob = viewModel.isDefaultScreenState.onEach(screenStates::add).launchIn(
            CoroutineScope(UnconfinedTestDispatcher(testScheduler))
        )

        val productsJob = viewModel.productsState.onEach(productsStates::add).launchIn(
            CoroutineScope(UnconfinedTestDispatcher(testScheduler))
        )

        viewModel.fetchSearchProducts("")

        verify(timeout = 5000L) { searchUseCases.fetchSearchProducts(any()) }
        assertEquals(true, screenStates[0])
        assertEquals(false, screenStates[1])
        assertEquals(
            PagingData.empty<Product>().collectData(UnconfinedTestDispatcher(testScheduler)),
            productsStates[0].collectData(UnconfinedTestDispatcher(testScheduler))
        )

        screenJob.cancel()
        productsJob.cancel()
    }
}
