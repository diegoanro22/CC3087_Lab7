package com.example.cc3087_lab7.navigation

sealed class Screens (val route: String) {
    data object CategoriesScreen: Screens("Categories")
    data object MealsScreen: Screens("Meals")
    data object MealDetailsScreen: Screens("Details")
}


