package com.coolbluetest.usecase

import com.coolbluetest.common.BaseTest
import com.coolbluetest.common.MockedData
import com.coolbluetest.common.getData
import com.coolbluetest.domain.repository.SearchRepository
import com.coolbluetest.domain.usecase.search.FetchSearchProductsUseCase
import io.mockk.*
import org.junit.Test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class FetchSearchProductsUseCaseTest : BaseTest() {

    private val repository: SearchRepository = mockk()
    private val useCase = FetchSearchProductsUseCase(repository)

    @Test
    fun `fetch search products returns expected data`() = runTest {
        every { repository.fetchSearchProducts(any()) } returns
            MockedData.getProductsFlowPagingData()

        val actualDataResponse = useCase("").getData(
            UnconfinedTestDispatcher(testScheduler)
        )

        val expectedResponse = MockedData.getProductsFlowPagingData().getData(
            UnconfinedTestDispatcher(testScheduler)
        )

        verify(timeout = 5000L) { repository.fetchSearchProducts(any()) }
        assertEquals(expectedResponse, actualDataResponse)
    }
}
