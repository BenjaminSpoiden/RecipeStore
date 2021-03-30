package com.ben.recipestore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ben.recipestore.repository.RecipeStoreRepository
import com.ben.recipestore.ui.home.HomeViewModel
import javax.inject.Inject


class ViewModelFactory @Inject constructor(private val recipeStoreRepository: RecipeStoreRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(recipeStoreRepository) as T
            else -> throw Exception("Couldn't find the view model class.")
        }
    }
}