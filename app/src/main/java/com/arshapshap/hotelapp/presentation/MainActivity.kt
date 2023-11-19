package com.arshapshap.hotelapp.presentation

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.arshapshap.hotelapp.R
import com.arshapshap.hotelapp.core.presentation.BaseActivity
import com.arshapshap.hotelapp.databinding.ActivityMainBinding
import com.arshapshap.hotelapp.navigation.Navigator
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding>(
    ActivityMainBinding::inflate
) {

    private val navigator: Navigator by inject()

    override fun initViews() {
        configureToolbar(getNavController())
        navigator.attachNavController(getNavController(), R.navigation.nav_graph_main)
    }

    override fun onDestroy() {
        navigator.detachNavController(getNavController())
        super.onDestroy()
    }

    private fun configureToolbar(navController: NavController) {
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun getNavController() =
        (supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment).navController
}