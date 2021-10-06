package com.dicoding.picodiploma.myrecipesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.myrecipesapp.RecipeResponse
import com.dicoding.picodiploma.myrecipesapp.databinding.ActivityMainBinding
import com.dicoding.picodiploma.myrecipesapp.network.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isLoading(true)
        CoroutineScope(Dispatchers.IO).launch {
            getRecipes()
        }
    }

    private suspend fun getRecipes() {
        val response = ApiConfig.getApiService().getRecipes()
        val results = response.results
        runOnUiThread {
            showRecipes(results)
        }
    }

    fun showRecipes(recipes: List<RecipeResponse>) {
        val adapter = RecipeAdapter(recipes)
        binding.rvRecipe.layoutManager = LinearLayoutManager(this)
        binding.rvRecipe.adapter = adapter
        isLoading(false)
    }

    fun isLoading(state: Boolean) {
        binding.progressBar.isVisible = state
    }
}