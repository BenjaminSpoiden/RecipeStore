package com.ben.recipestore.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ben.recipestore.R
import com.ben.recipestore.databinding.HomeFragmentBinding
import com.ben.recipestore.model.FailureResponse
import com.ben.recipestore.network.ResponseHandler
import com.ben.recipestore.network.Status
import com.ben.recipestore.repository.RecipeStoreRepository
import com.ben.recipestore.ui.BaseFragment
import com.ben.recipestore.ui.adapter.RecipeAdapter
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>(), SearchView.OnQueryTextListener {

    @Inject lateinit var glideRequestManager: RequestManager

    private val recipeAdapter by lazy { RecipeAdapter(glideRequestManager) }
    private lateinit var recipeRecyclerView: RecyclerView

    override fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): HomeFragmentBinding {
        return HomeFragmentBinding.inflate(inflater, container, false)
    }

    override fun bindViewModel(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setupRecyclerView()
        lifecycleScope.launchWhenStarted {
            viewModel.searchRecipes.collect {
                when(it.status) {
                    Status.LOADING -> {
                        Log.d("Tag", "Loading Data...")
                    }
                    Status.SUCCESS -> {
                        it.data?.results?.let { results ->
                            recipeAdapter.addItems(results)
                        }
                    }
                    Status.FAILURE -> {
                        it.responseBody?.let { responseBody ->
                            Log.d("Tag", "${convertResponseToDataClass<FailureResponse>(responseBody)}")
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        recipeRecyclerView = binding.recipeRecyclerView
        recipeRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = recipeAdapter
        }
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
            viewModel.searchRecipes(it)
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