package com.example.cc3087_lab7.networking

import com.example.cc3087_lab7.model.CategoryResponse
import com.example.cc3087_lab7.model.MealDetailsResponse
import com.example.cc3087_lab7.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): MealDetailsResponse
}