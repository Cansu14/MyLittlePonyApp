package com.example.mylittlepony.data

data class DataResponse(
    val data : List<PonyData>
)

data class PonyDetailResponse(
    val data: List<PonyData>
)

data class PonyData(
    val id: Int,
    val name : String,
    val image: List<String>,
    val sex: String?,
    val occupation: String?
)

