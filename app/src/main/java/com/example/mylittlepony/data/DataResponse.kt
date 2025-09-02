package com.example.mylittlepony.data
data class DataResponse(
    val data : List<PonyData>
)

data class PonyData(
    val name : String,
    val image: List<String>
)
