package com.ben.recipestore.model.search


import com.google.gson.annotations.SerializedName

data class Result(
    val calories: Int,
    val carbs: String,
    val fat: String,
    val id: Int,
    val image: String,
    val imageType: String,
    val protein: String,
    val title: String
)