package com.ben.recipestore.model


import com.google.gson.annotations.SerializedName

data class Step(
    val equipment: List<Equipment>,
    val ingredients: List<Ingredient>,
    val length: Length,
    val number: Int,
    val step: String
)