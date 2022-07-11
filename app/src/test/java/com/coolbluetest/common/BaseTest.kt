package com.coolbluetest.common

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
open class BaseTest {

    private val dispatcher = newSingleThreadContext("UI Thread")

    @Before
    open fun setUp(){
        Dispatchers.setMain(dispatcher)
    }

    @After
    open fun tearDown(){
        Dispatchers.resetMain()
        dispatcher.close()
    }
}
