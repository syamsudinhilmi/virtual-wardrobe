package com.playdeadrespawn.virtualwardrobe.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.playdeadrespawn.virtualwardrobe.data.Repository
import com.playdeadrespawn.virtualwardrobe.model.Order
import com.playdeadrespawn.virtualwardrobe.model.Wardrobe
import com.playdeadrespawn.virtualwardrobe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel (private val repository: Repository): ViewModel(){
    private val _uiState: MutableStateFlow<UiState<Order>> = MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<Order>> get() = _uiState

    fun getOrderById(orderId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderById(orderId))
        }
    }

    fun addToCart(order: Wardrobe, count: Int){
        viewModelScope.launch {
            repository.updateOrder(order.id, count)
        }
    }
}