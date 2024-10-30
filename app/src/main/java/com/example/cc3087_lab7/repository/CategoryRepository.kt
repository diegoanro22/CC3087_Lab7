package com.example.cc3087_lab7.repository

import com.example.cc3087_lab7.model.Category
import com.example.cc3087_lab7.networking.RetroFitInstance
import com.example.cc3087_lab7.networking.ApiService


class CategoryRepository (private val retroFitInstance: RetroFitInstance = RetroFitInstance()) {
    suspend fun getCategories() : List<Category> {
        return retroFitInstance.getCategories().categoryResponse
    }
}