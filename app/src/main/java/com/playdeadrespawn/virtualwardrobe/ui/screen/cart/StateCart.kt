package com.playdeadrespawn.virtualwardrobe.ui.screen.cart

import com.playdeadrespawn.virtualwardrobe.model.Order

data class StateCart(
    val order: List<Order>,
    val totalPrice: Int
)