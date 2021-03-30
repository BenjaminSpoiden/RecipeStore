package com.ben.recipestore.network

import com.ben.recipestore.model.Recipes
import com.ben.recipestore.model.search.SearchResponse
import com.ben.recipestore.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeStoreService {
    @GET("/recipes/complexSearch?apiKey=$API_KEY&number=1")
    suspend fun searchRecipes(@Query("query") query: String): SearchResponse
}