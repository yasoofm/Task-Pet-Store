package com.example.petsstore.singleton

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val url = "https://coded-pets-api-crud.eapi.joincoded.com/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}