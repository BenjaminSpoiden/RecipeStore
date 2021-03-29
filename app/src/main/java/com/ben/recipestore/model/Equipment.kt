package com.ben.recipestore.model


import com.google.gson.annotations.SerializedName

data class Equipment(
    val id: Int,
    val image: String,
    val localizedName: String,
    val name: String,
    val temperature: Temperature
)