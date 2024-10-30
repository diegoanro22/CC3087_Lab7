package com.plataformas.lab7.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cc3087_lab7.model.RecipeDetail
import com.example.cc3087_lab7.repository.MealDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val repository: MealDetailRepository) : ViewModel() {

    private val _mealDetail = MutableStateFlow<RecipeDetail?>(null)
    val mealDetail: StateFlow<RecipeDetail?> = _mealDetail

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun fetchMealDetails(id: String) {
        Log.d("TAG_COROUTINES", "Fetching details for meal ID: $id")

        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Llama al método getMealDetails del repositorio
                val fetchedMealDetail = repository.getMealDetails(id)

                // Actualiza el estado solo si se encuentra un detalle válido.
                if (fetchedMealDetail != null) {
                    _mealDetail.value = fetchedMealDetail
                    Log.d("TAG_COROUTINES", "Details loaded for meal ID: $id")
                } else {
                    _errorMessage.value = "No meal details found for ID: $id"
                }
            } catch (e: Exception) {
                // Maneja el error y actualiza el mensaje de error
                Log.e("TAG_COROUTINES", "Exception: $e")
                _errorMessage.value = e.message ?: "Unknown error"
            }
        }
    }
}
