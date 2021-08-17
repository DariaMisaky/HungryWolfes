package com.example.hungrywolfes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolfes.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment


        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangedListener)


        findViewById<BottomNavigationView>(R.id.bottom_navigation)?.setupWithNavController(
            navController
        )

    }

    private val destinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val bottomNavVisibility = when (destination.id) {
                R.id.homeScreen, R.id.favoriteFragment, R.id.userFragment -> true
                else -> false
            }
           findViewById<BottomNavigationView>(R.id.bottom_navigation).visibility =
                if (bottomNavVisibility) View.VISIBLE else View.GONE
        }

}