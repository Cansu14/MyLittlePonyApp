package com.example.mylittlepony.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.mylittlepony.data.PonyData
import com.example.mylittlepony.data.PonyPagingSource
import com.example.mylittlepony.data.Repository
import com.example.mylittlepony.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PonyListViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(UiState<Flow<PagingData<PonyData>>>(isLoading = true))
    val uiState: StateFlow<UiState<Flow<PagingData<PonyData>>>> = _uiState

    init {
        fetchPonyData()
    }

    private fun fetchPonyData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UiState(isLoading = true)
            try {
                val pagingFlow: Flow<PagingData<PonyData>> = Pager(
                    config = PagingConfig(
                        pageSize = 20, enablePlaceholders = false
                    ),
                    pagingSourceFactory = { PonyPagingSource(repository) }
                ).flow.cachedIn(viewModelScope)

                withContext(Dispatchers.Main) {
                    _uiState.value = UiState(data = pagingFlow)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _uiState.value = UiState(error = e.message)
                }
                Log.e("DATA ERROR", "DATA CANT FETCH")
            }
        }
    }

}