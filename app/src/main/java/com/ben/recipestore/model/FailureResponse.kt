package com.ben.recipestore.model


import com.google.gson.annotations.SerializedName

data class FailureResponse(
    val code: Int,
    val message: String,
    val status: String
)