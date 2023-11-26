package com.playdeadrespawn.virtualwardrobe.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playdeadrespawn.virtualwardrobe.data.Repository
import com.playdeadrespawn.virtualwardrobe.model.Order
import com.playdeadrespawn.virtualwardrobe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<Order>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Order>>> get() = _uiState

    fun getAllFashion() {
        viewModelScope.launch {
            repository.getAllFashion()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            repository.searchOrder(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

}