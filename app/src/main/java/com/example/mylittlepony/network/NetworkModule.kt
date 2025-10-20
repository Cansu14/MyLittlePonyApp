package com.example.mylittlepony.network

import com.example.mylittlepony.data.Repository
import com.example.mylittlepony.data.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ponyapi.net/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PonyApiService {
        return retrofit.create(PonyApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: PonyApiService): Repository {
        return RepositoryImpl(apiService)
    }
}