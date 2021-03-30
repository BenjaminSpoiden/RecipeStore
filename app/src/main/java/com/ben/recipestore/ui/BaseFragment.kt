package com.ben.recipestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ben.recipestore.repository.RecipeStoreRepository
import com.google.gson.Gson
import okhttp3.ResponseBody
import javax.inject.Inject

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel> : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private var _viewModel: VM? = null
    protected val viewModel get() = _viewModel!!

    @Inject lateinit var recipeStoreRepository: RecipeStoreRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindFragment(inflater, container, savedInstanceState)
        _viewModel = ViewModelProvider(this, ViewModelFactory(recipeStoreRepository)).get(bindViewModel())
        return binding.root
    }

    abstract fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): VB
    abstract fun bindViewModel(): Class<VM>

    protected inline fun <reified T> convertResponseToDataClass(responseBody: ResponseBody): T = Gson().fromJson(responseBody.string(), T::class.java)

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}