package com.coolbluetest.common

import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlin.coroutines.CoroutineContext

suspend fun <T : Any> PagingData<T>.collectData(dispatcher: CoroutineContext): List<T> {
    val differCallback = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {}
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
    }
    val items = mutableListOf<T>()
    val differ = object : PagingDataDiffer<T>(differCallback, dispatcher) {
        override suspend fun presentNewList(
            previousList: NullPaddedList<T>,
            newList: NullPaddedList<T>,
            lastAccessedIndex: Int,
            onListPresentable: () -> Unit
        ): Int? {
            for (i in 0 until newList.size)
                items.add(newList.getFromStorage(i))
            onListPresentable()
            return null
        }
    }
    differ.collectFrom(this)
    return items
}

suspend fun <T : Any> Flow<PagingData<T>>.getData(coroutineContext: CoroutineContext) =
    this.take(1)
        .toList()
        .first()
        .collectData(coroutineContext)
