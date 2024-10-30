package com.example.cc3087_lab7.repository

import com.example.cc3087_lab7.model.RecipeDetail
import com.example.cc3087_lab7.networking.ApiService

class MealDetailRepository(private val apiService: ApiService) {

    suspend fun getMealDetails(id: String): RecipeDetail? {
        // Verifica si la lista de meals está vacía y devuelve el primer elemento si existe.
        val meals = apiService.getMealDetails(id).meals
        return meals.firstOrNull() // Devuelve el primer elemento o null si la lista está vacía.
    }
}
