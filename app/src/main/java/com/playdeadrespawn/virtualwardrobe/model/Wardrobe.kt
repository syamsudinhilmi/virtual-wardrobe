package com.playdeadrespawn.virtualwardrobe.model

data class Wardrobe(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val image: String,
)