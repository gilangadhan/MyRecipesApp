package com.dicoding.picodiploma.myrecipesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.myrecipesapp.R
import com.dicoding.picodiploma.myrecipesapp.RecipeResponse
import com.dicoding.picodiploma.myrecipesapp.RecipeResponses
import com.dicoding.picodiploma.myrecipesapp.databinding.ActivityMainBinding
import com.dicoding.picodiploma.myrecipesapp.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getRecipes()
    }

    private fun getRecipes() {
        val client = ApiConfig.getApiService().getRecipes()
        client.enqueue(object : Callback<RecipeResponses> {
            override fun onResponse(call: Call<RecipeResponses>, response: Response<RecipeResponses>) {
                response.body()?.let {
                    showRecipes(it.results)
                }
            }

            override fun onFailure(call: Call<RecipeResponses>, t: Throwable) {
                println("data gagal didapatkan, error $t")
            }

        })
    }

    fun showRecipes(recipes: List<RecipeResponse>){
        val adapter = RecipeAdapter(recipes)
        binding.rvRecipe.layoutManager = LinearLayoutManager(this)
        binding.rvRecipe.adapter = adapter
    }
}