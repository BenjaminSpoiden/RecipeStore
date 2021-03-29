package com.ben.recipestore.model


import com.google.gson.annotations.SerializedName

data class Temperature(
    val number: Double,
    val unit: String
)