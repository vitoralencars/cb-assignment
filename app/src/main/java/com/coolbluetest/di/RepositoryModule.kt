package com.coolbluetest.di

import com.coolbluetest.data.source.remote.SearchService
import com.coolbluetest.data.repository.SearchRepositoryImpl
import com.coolbluetest.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesSearchRepository(service: SearchService): SearchRepository =
        SearchRepositoryImpl(service)
}
