package com.ben.recipestore.model.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val number: Int,
    val offset: Int,
    val results: List<Result>,
    val totalResults: Int
)