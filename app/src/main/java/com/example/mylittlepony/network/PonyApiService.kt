package com.example.mylittlepony.network

import com.example.mylittlepony.data.DataResponse
import com.example.mylittlepony.data.PonyDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PonyApiService {
    @GET("character/all")
    suspend fun getPonyData(
        @retrofit2.http.Query("page") page: Int = 1,
    ): DataResponse

    @GET("character/{id}")
    suspend fun getPonyDetail(@Path("id") id: Int): PonyDetailResponse
}