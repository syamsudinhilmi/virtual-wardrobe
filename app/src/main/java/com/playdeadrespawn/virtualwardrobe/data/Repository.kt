package com.playdeadrespawn.virtualwardrobe.data

import com.playdeadrespawn.virtualwardrobe.model.DataSource
import com.playdeadrespawn.virtualwardrobe.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class Repository {

    private val order = mutableListOf<Order>()

    init {
        if (order.isEmpty()) {
            DataSource.dummyData.forEach {
                order.add(Order(it, 0))
            }
        }
    }

    fun getAllFashion(): Flow<List<Order>> {
        return flowOf(order)
    }

    fun getOrderById(rewardId: Long): Order {
        return order.first {
            it.wardrobe.id == rewardId
        }
    }

    fun updateOrder(id: Long, newCountValue: Int): Flow<Boolean> {
        val index = order.indexOfFirst { it.wardrobe.id == id }
        val result = if (index >= 0) {
            val orderFashion = order[index]
            order[index] =
                orderFashion.copy(wardrobe = orderFashion.wardrobe, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrder(): Flow<List<Order>> {
        return getAllFashion()
            .map {
                it.filter { orderFashion ->
                    orderFashion.count != 0
                }
            }
    }

    fun searchOrder(query: String): Flow<List<Order>> {
        return getAllFashion()
            .map {
                it.filter { orderFashion ->
                    orderFashion.wardrobe.name.contains(query, ignoreCase = true)
                }
            }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}