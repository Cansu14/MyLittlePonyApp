package com.example.mylittlepony.data

import com.example.mylittlepony.network.PonyApiService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: PonyApiService,
) : Repository {
    override suspend fun getPonyData(page: Int): DataResponse {
        return apiService.getPonyData(page)
    }

    override suspend fun getPonyDetail(id: Int): PonyData {
        return apiService.getPonyDetail(id).data.first()
    }
}