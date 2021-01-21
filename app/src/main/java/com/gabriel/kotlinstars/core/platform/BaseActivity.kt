package com.gabriel.kotlinstars.core.platform

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.gabriel.kotlinstars.R

abstract class BaseActivity(@LayoutRes layoutIdRes : Int = 0) : AppCompatActivity(layoutIdRes) {

    private var navController: NavController? = null
    private var navDestination: NavDestination? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setUpNavController()
    }
    private fun setUpNavController() {
        val host: NavHostFragment? = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment?

        navController = host?.navController ?: Navigation.findNavController(this, R.id.nav_host)
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            onDestinationChangedListener(destination)
        }

    }

    private fun onDestinationChangedListener(
        destination: NavDestination,
    ) {
        navDestination = destination

    }
}
