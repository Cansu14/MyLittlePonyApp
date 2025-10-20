package com.example.mylittlepony.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylittlepony.data.Repository
import com.example.mylittlepony.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PonyDetailViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState<Any>(isLoading = true))
    val uiState: StateFlow<UiState<Any>> = _uiState



    fun fetchPonyDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                withContext(Dispatchers.Main) {
                    val detail = repository.getPonyDetail(id)
                    _uiState.value = UiState(data = detail)
                    Log.d("DETAIL", "OK id=$id -> $detail")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = UiState(error = e.message)
                    Log.e("DETAIL", "FAILED id=$id", e)
                }
            }
        }
    }
}