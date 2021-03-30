package com.ben.recipestore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ben.recipestore.model.search.SearchResponse
import com.ben.recipestore.network.ResponseHandler
import com.ben.recipestore.repository.RecipeStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val recipeStoreRepository: RecipeStoreRepository) : ViewModel() {

    private var _searchRecipes: MutableStateFlow<ResponseHandler<SearchResponse>> = MutableStateFlow(ResponseHandler.onLoading("Fetching data..."))
    val searchRecipes: StateFlow<ResponseHandler<SearchResponse>> get() = _searchRecipes

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _searchRecipes.value = recipeStoreRepository.searchRecipes(query)
        }
    }
}