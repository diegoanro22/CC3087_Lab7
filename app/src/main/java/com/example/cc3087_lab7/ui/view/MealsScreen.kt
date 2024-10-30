package com.example.cc3087_lab7.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.cc3087_lab7.model.Recipe
import com.plataformas.lab7.viewmodel.MealViewModel


@Composable
fun MealsScreen(navController: NavController, categoryName: String, viewModel: MealViewModel = viewModel()) {
    LaunchedEffect(categoryName) {
        viewModel.fetchMealsByCategory(categoryName)
    }

    val meals by viewModel.meals.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        item {
            Text(
                text = "Comidas en $categoryName",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
        items(meals) { meal ->
            MealItem(meal, navController)
        }
    }
}


@Composable
fun MealItem(meal: Recipe, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("meal_details/${meal.idMeal}") }
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(data = meal.strMealThumb),
                contentDescription = meal.strMeal,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = meal.strMeal, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = meal.idMeal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
