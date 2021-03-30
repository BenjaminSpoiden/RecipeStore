package com.ben.recipestore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: ViewModel>: Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    private var _viewModel: VM? = null
    protected val viewModel get() = _viewModel!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = bindFragment(inflater, container, savedInstanceState)
        _viewModel = ViewModelProvider(this, ViewModelFactory()).get(bindViewModel())
        return binding.root
    }

    abstract fun bindFragment(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): VB
    abstract fun bindViewModel(): Class<VM>

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}