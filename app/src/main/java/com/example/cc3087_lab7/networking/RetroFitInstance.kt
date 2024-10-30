package com.example.cc3087_lab7.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.cc3087_lab7.model.CategoryResponse
import com.example.cc3087_lab7.model.MealDetailsResponse
import com.example.cc3087_lab7.model.MealResponse

class RetroFitInstance(){

    private lateinit var apiService: ApiService

    init {
        val retrofit =
            Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getCategories(): CategoryResponse {
        return apiService.getCategories()
    }

    suspend fun getMealsByCategory(category: String): MealResponse {
        return apiService.getMealsByCategory(category)
    }

    suspend fun getMealDetails(id: String): MealDetailsResponse {
        return apiService.getMealDetails(id)
    }


}