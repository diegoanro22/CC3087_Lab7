package com.example.cc3087_lab7.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cc3087_lab7.ui.view.CategoriesScreen
import com.example.cc3087_lab7.ui.view.MealDetailsScreen
import com.example.cc3087_lab7.ui.view.MealsScreen
import androidx.compose.ui.Modifier

@Composable
fun Navigation(navController: NavHostController, innerPadding: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screens.CategoriesScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Screens.CategoriesScreen.route) {
            CategoriesScreen(navController)
        }
        composable(Screens.MealsScreen.route + "/{categoryName}") { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName")
            categoryName?.let {
                MealsScreen(navController, it)
            }
        }
        composable(Screens.MealDetailsScreen.route + "/{recipeId}") { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId")
            recipeId?.let {
                MealDetailsScreen(navController, it)
            }
        }
    }
}
