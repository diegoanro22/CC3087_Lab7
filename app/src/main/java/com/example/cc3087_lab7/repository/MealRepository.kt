package com.example.cc3087_lab7.repository

import com.example.cc3087_lab7.model.Recipe
import com.example.cc3087_lab7.networking.RetroFitInstance

class MealRepository (private val retroFitInstance: RetroFitInstance = RetroFitInstance()) {
    suspend fun getMealsByCategory(category: String) : List<Recipe> {
        return retroFitInstance.getMealsByCategory(category).meals
    }

}