package com.plataformas.lab7.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cc3087_lab7.model.Recipe
import com.example.cc3087_lab7.repository.MealRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealViewModel(private val repository: MealRepository = MealRepository()) : ViewModel() {

    private val _meals = MutableStateFlow<List<Recipe>>(emptyList())
    val meals: StateFlow<List<Recipe>> = _meals

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun fetchMealsByCategory(category: String) {
        Log.d("TAG_COROUTINES", "Fetching meals for category: $category")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val fetchedMeals = repository.getMealsByCategory(category)
                _meals.value = fetchedMeals
                Log.d("TAG_COROUTINES", "Meals loaded for category: $category")
            } catch (e: Exception) {
                Log.e("TAG_COROUTINES", "Exception: $e")
                _errorMessage.value = e.message ?: "Unknown error"
            }
        }
    }
}
