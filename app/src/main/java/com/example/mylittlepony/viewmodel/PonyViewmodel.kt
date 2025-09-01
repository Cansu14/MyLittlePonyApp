package com.example.mylittlepony.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylittlepony.data.PonyData
import com.example.mylittlepony.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PonyViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _ponyData = MutableStateFlow<List<PonyData>>(emptyList())
    val ponyData: StateFlow<List<PonyData>> = _ponyData

    init {
        fetchPonyData()
    }

    private fun fetchPonyData() {
        viewModelScope.launch {
            try {
                _ponyData.value = repository.getPonyData().data
            }catch (e: Exception){
                Log.e("DATA ERROR","DATA CANT FETCH")
            }
        }
    }
}