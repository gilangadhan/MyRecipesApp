package com.dicoding.picodiploma.myrecipesapp

data class RecipeResponse(
    val title: String,
    val thumb: String,
    val key: String,
    val times: String,
)

data class RecipeResponses(val results: List<RecipeResponse>)