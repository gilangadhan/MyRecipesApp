package com.dicoding.picodiploma.myrecipesapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    fun getApiService(): ApiInterface {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://masak-apa.tomorisakura.vercel.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}