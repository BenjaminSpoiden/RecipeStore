package com.ben.recipestore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ben.recipestore.ui.home.HomeViewModel


class ViewModelFactory(): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel() as T
            else -> throw Exception("Couldn't find the view model class.")
        }
    }
}