package com.ben.recipestore.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import com.ben.recipestore.R
import com.ben.recipestore.databinding.HomeFragmentBinding
import com.ben.recipestore.ui.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(), SearchView.OnQueryTextListener {

    companion object {
        fun newInstance() = HomeFragment()
    }


    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }

    override fun bindViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.imageView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_fragment_menu, menu)

        val searchItem = menu.findItem(R.id.home_fragment_search)
        val searchView = searchItem.actionView as? SearchView

        searchView?.maxWidth = Int.MAX_VALUE
        searchView?.setOnQueryTextListener(this)
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            Log.d("Tag", "$newText")
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let {
            Log.d("Tag", "Query: $it")
        }
        return true
    }
}