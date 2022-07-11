package com.coolbluetest.di

import com.coolbluetest.data.source.remote.SearchService
import com.coolbluetest.data.source.remote.ServiceConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(ServiceConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(ServiceConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(ServiceConstants.TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ServiceConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun providesSearchService(retrofit: Retrofit): SearchService = retrofit
        .create(SearchService::class.java)
}
