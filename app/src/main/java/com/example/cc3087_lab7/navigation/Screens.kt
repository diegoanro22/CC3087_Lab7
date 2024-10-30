package com.example.cc3087_lab7.navigation

sealed class Screens (val route: String) {
    data object CategoriesScreen: Screens("category_screen")
    data object MealsScreen: Screens("recipe_screen/{categoryName}")
    data object MealDetailsScreen: Screens("recipe_detail_screen/{recipeId}")
}


