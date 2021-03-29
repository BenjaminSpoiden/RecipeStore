package com.ben.recipestore.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ben.recipestore.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy { findNavController(R.id.fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onSetupBottomNavigation(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return  navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun onSetupBottomNavigation(navController: NavController) {
        val bottomNavController = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.menuFragment, R.id.mealPlannerFragment))

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavController.setupWithNavController(navController)
    }
}