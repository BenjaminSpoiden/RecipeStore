package com.ben.recipestore.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ben.recipestore.model.search.Result

class DiffUtilCallback: DiffUtil.ItemCallback<Result>() {

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem.id == newItem.id

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean =
            oldItem == newItem
}