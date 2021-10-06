package com.dicoding.picodiploma.myrecipesapp.network

import com.dicoding.picodiploma.myrecipesapp.RecipeResponses
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("api/recipes")
    fun getRecipes(): Call<RecipeResponses>
}