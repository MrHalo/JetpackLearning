package com.cr.coroutinesdemo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateFlowViewModel:ViewModel() {

    private val _uiState = MutableStateFlow<String> ("")
    val uiState:StateFlow<String> = _uiState
    fun buildUp(input:String){
        _uiState.value = "hello $input"
    }
}