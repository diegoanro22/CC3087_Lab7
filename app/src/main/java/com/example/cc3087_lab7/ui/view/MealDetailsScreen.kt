package com.example.cc3087_lab7.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.plataformas.lab7.viewmodel.RecipeDetailViewModel

@Composable
fun MealDetailsScreen(navController: NavController, recipeId: String, viewModel: RecipeDetailViewModel = viewModel()) {
    // Llama a fetchMealDetails una vez cuando se carga la pantalla
    LaunchedEffect(recipeId) {
        viewModel.fetchMealDetails(recipeId)
    }

    // Observa el flujo de mealDetail desde el ViewModel
    val mealDetail by viewModel.mealDetail.collectAsState(initial = null)
    val errorMessage by viewModel.errorMessage.collectAsState()

    if (errorMessage.isNotEmpty()) {
        // Muestra el mensaje de error si existe
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
    } else {
        // Muestra los detalles de la comida si existen
        mealDetail?.let { meal ->
            LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                item {
                    Image(
                        painter = rememberImagePainter(data = meal.strMealThumb),
                        contentDescription = meal.strMeal,
                        modifier = Modifier.fillMaxWidth().height(200.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = meal.strMeal, style = MaterialTheme.typography.titleLarge)
                    Text(text = "Category: ${meal.strCategory}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Area: ${meal.strArea}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Instructions:", style = MaterialTheme.typography.titleMedium)
                    Text(text = meal.strInstructions, style = MaterialTheme.typography.bodyMedium)
                }
                item {
                    Text(text = "Ingredients:", style = MaterialTheme.typography.titleMedium)
                    for (i in 1..20) {
                        val ingredient = meal::class.java.getDeclaredField("strIngredient$i").get(meal) as? String
                        val measure = meal::class.java.getDeclaredField("strMeasure$i").get(meal) as? String
                        if (!ingredient.isNullOrBlank()) {
                            Text(text = "$measure $ingredient", style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
