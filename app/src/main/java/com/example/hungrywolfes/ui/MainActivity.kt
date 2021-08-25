package com.example.hungrywolfes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.hungrywolfes.R
import com.example.hungrywolfes.databinding.ActivityMainBinding
import com.example.hungrywolfes.network.NetworkConnection

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(destinationChangedListener)

        binding.bottomNavigation.setupWithNavController(
            navController
        )

        checkInternetConnection()


    }


    private val destinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            val bottomNavVisibility = when (destination.id) {
                R.id.homeScreen, R.id.favoriteFragment, R.id.userFragment -> true
                else -> false
            }
            binding.bottomNavigation.visibility =
                if (bottomNavVisibility) View.VISIBLE else View.INVISIBLE
        }

    fun checkInternetConnection() {
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, { isConnected ->
            if (isConnected) {
                if (findNavController(R.id.nav_host_fragment).currentBackStackEntry?.destination?.id == R.id.internetFragment)
                    findNavController(R.id.nav_host_fragment).popBackStack()
            } else {
                findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_internetFragment)
            }
        })

    }
}