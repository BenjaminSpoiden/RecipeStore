package com.ben.recipestore.ui.meal_planner

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ben.recipestore.R

class MealPlannerFragment : Fragment() {

    companion object {
        fun newInstance() = MealPlannerFragment()
    }

    private lateinit var viewModel: MealPlannerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.meal_planner_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MealPlannerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}