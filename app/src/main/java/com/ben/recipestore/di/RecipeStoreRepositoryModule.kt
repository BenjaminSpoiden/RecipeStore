package com.ben.recipestore.di

import com.ben.recipestore.network.RecipeStoreService
import com.ben.recipestore.repository.RecipeStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RecipeStoreRepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeStoreRepository(recipeStoreService: RecipeStoreService) = RecipeStoreRepository(recipeStoreService)
}