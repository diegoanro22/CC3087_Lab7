package com.example.cc3087_lab7.networking

import retrofit2.http.GET
import retrofit2.http.Query


interface API_SERVICE {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): MealDetailsResponse
}