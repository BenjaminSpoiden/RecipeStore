package com.ben.recipestore.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ben.recipestore.databinding.RecipeItemBinding
import com.ben.recipestore.model.search.Result
import com.ben.recipestore.model.search.SearchResponse
import com.bumptech.glide.RequestManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeAdapter constructor(private val glideRequestManager: RequestManager): ListAdapter<Result, RecipeAdapter.RecipeViewHolder>(DiffUtilCallback()) {

    private val recipeAdapterScope = CoroutineScope(Dispatchers.IO)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
            RecipeViewHolder.from(parent)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position), glideRequestManager)
    }

    fun addItems(items: List<Result>) {
        recipeAdapterScope.launch {
            withContext(Dispatchers.Main) {
                submitList(items) {
                    Log.d("Tag", "Test Callback: ${items.size}")
                }
            }
        }
    }

    class RecipeViewHolder private constructor(private val recipeItemBinding: RecipeItemBinding): RecyclerView.ViewHolder(recipeItemBinding.root) {

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = RecipeItemBinding.inflate(layoutInflater, parent, false)
                return RecipeViewHolder(view)
            }
        }

        fun bind(item: Result, glideRequestManager: RequestManager) {

        }
    }
}