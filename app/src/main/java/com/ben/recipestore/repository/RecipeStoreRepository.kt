package com.ben.recipestore.repository

import com.ben.recipestore.network.RecipeStoreService

class RecipeStoreRepository(private val recipeStoreService: RecipeStoreService): BaseRepository() {

    suspend fun searchRecipes(query: String) = callHandler {
        recipeStoreService.searchRecipes(query)
    }
}