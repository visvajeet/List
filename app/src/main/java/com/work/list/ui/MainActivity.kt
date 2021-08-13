package com.work.list.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.work.list.R
import com.work.list.databinding.ActivityMainBinding
import com.work.list.utils.CheckNetwork
import com.work.list.utils.showToast
import com.work.list.viewmodels.SharedViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    private val viewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpNavigationWithBottomNav()

        lifecycleScope.launchWhenResumed { loadData() }

    }

    private fun setUpNavigationWithBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostContainer) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavMenu.setupWithNavController(navController)

    }

    private fun loadData() {
        if (!CheckNetwork.isNetworkConnected(this@MainActivity)) {
            showToast(getString(R.string.no_internet))
            return
        }
        viewModel.refreshData()
    }
}