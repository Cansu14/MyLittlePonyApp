package com.example.mylittlepony.data

import com.example.mylittlepony.network.PonyApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: PonyApiService
) {
    suspend fun getPonyData() = apiService.getPonyData()
}