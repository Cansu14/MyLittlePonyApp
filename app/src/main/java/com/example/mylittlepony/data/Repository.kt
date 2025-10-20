package com.example.mylittlepony.data

interface Repository{
    suspend fun getPonyData(page:Int) : DataResponse

    suspend fun getPonyDetail(id: Int): PonyData
}