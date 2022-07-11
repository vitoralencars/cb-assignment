package com.coolbluetest.paging

import androidx.paging.PagingSource
import com.coolbluetest.common.BaseTest
import com.coolbluetest.common.MockedData
import com.coolbluetest.data.source.remote.SearchService
import com.coolbluetest.domain.paging.SearchPagingSource
import com.coolbluetest.ui.screen.search.util.SearchException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
class SearchPagingSourceTest : BaseTest() {

    private lateinit var pagingSource: SearchPagingSource

    private val service: SearchService = mockk()

    private val mockedQueries = listOf("q1", "q2")

    @Test
    fun `fetch search products success has more products to load`() = runTest {
        coEvery { service.fetchSearchProducts(any(), any()) } returns MockedData.getMockedResults()

        val expectedResponse = PagingSource.LoadResult.Page(
            data = MockedData.getMockedProducts(),
            prevKey = MockedData.getLoadParams().key?.minus(1),
            nextKey = MockedData.getLoadParams().key?.plus(1),
        )

        pagingSource = SearchPagingSource(service, mockedQueries[1])

        val actualResponse = pagingSource.load(MockedData.getLoadParams())

        coVerify(timeout = 5000L) { service.fetchSearchProducts(any(), any()) }
        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `fetch search products success has loaded all products`() = runTest {
        coEvery { service.fetchSearchProducts(any(), any()) } returns MockedData.getMockedResults(
            totalResults = 0
        )

        val expectedResponse = PagingSource.LoadResult.Page(
            data = MockedData.getMockedResults(totalResults = 0).products,
            prevKey = MockedData.getLoadParams().key?.minus(1),
            nextKey = null
        )

        pagingSource = SearchPagingSource(service, mockedQueries[0])

        val actualResponse = pagingSource.load(MockedData.getLoadParams())

        coVerify(timeout = 5000L) { service.fetchSearchProducts(any(), any()) }
        assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `fetch search products error`() = runTest {
        val errorException: Exception = mockk()
        val searchException = SearchException(
            queryError = mockedQueries[0],
            throwable = errorException
        )

        coEvery { service.fetchSearchProducts(any(), any()) } throws errorException

        val expectedResponse = PagingSource.LoadResult.Error<Any, Any>(searchException)

        pagingSource = SearchPagingSource(service, mockedQueries[0])

        val actualResponse = pagingSource.load(MockedData.getLoadParams())

        coVerify(timeout = 5000L) { service.fetchSearchProducts(any(), any()) }
        assertEquals(expectedResponse, actualResponse)
    }
}
