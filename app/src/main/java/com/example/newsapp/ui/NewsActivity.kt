package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.db.AppDatabase
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.ui.viewmodel.NewsViewModel
import com.example.newsapp.ui.viewmodel.NewsViewModelFactory

class NewsActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var binding: ActivityNewsBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setTheme(R.style.AppTheme)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val newsRepository = NewsRepository(AppDatabase(this))
        val viewModelProviderFactory = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.breakingNewsFragment,
            R.id.savedNewsFragment,
            R.id.searchNewsFragment))

        binding.bottomNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController,appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}