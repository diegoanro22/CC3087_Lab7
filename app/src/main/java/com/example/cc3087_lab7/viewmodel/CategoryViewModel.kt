package com.plataformas.lab7.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cc3087_lab7.model.Category
import com.example.cc3087_lab7.repository.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository = CategoryRepository()) : ViewModel() {

    // StateFlow para categorías y mensajes de error
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        fetchCategories()
    }

    // Función para cargar las categorías desde el repositorio
    private fun fetchCategories() {
        Log.d("TAG_COROUTINES", "About to launch a coroutine")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("TAG_COROUTINES", "Launching a coroutine")
                val meals = repository.getCategories()
                Log.d("TAG_COROUTINES", "Data received")
                _categories.value = meals
            } catch (e: Exception) {
                Log.e("TAG_COROUTINES", "Exception: $e")
                _errorMessage.value = e.message ?: "Unknown error"
            }
        }
    }
}
