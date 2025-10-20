package com.example.mylittlepony.ui

data class UiState<T>(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: T? = null,
)