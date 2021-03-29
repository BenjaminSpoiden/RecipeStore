package com.ben.recipestore.di

import android.content.Context
import com.ben.recipestore.RecipeStoreApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RecipeStoreModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) = app as RecipeStoreApplication

    @Singleton
    @Provides
    fun provideString() = "Some string"
}