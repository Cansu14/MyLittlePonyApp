package com.example.mylittlepony.network

import com.example.mylittlepony.data.DataResponse
import retrofit2.http.GET

interface PonyApiService {
    @GET("character/all")
    suspend fun getPonyData() : DataResponse
}