package com.playdeadrespawn.virtualwardrobe.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playdeadrespawn.virtualwardrobe.data.Repository
import com.playdeadrespawn.virtualwardrobe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<StateCart>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<StateCart>> get() = _uiState

    fun getAddedOrder() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrder()
                .collect { order ->
                    val totalRequiredPoint = order.sumOf { it.wardrobe.price * it.count }
                    _uiState.value = UiState.Success(StateCart(order, totalRequiredPoint))
                }
        }
    }

    fun updateOrder(id: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrder(id, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrder()
                    }
                }
        }
    }
}