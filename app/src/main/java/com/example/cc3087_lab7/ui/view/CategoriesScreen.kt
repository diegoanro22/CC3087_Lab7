package com.example.cc3087_lab7.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.cc3087_lab7.model.Category
import com.plataformas.lab7.viewmodel.CategoryViewModel

@Composable
fun CategoriesScreen(navController: NavController) {
    val viewModel: CategoryViewModel = viewModel()
    val categories by viewModel.categories.collectAsState()

    when {
        categories.isEmpty() -> {

        }
        else -> {
            LazyColumn(contentPadding = PaddingValues(8.dp)) {
                item {
                    Text(
                        text = "Categorías",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                items(categories) { category ->
                    CategoryItem(category, navController)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(category: Category, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate("meals_screen/${category.name}") }
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Image(
                painter = rememberImagePainter(data = category.imageUrl),
                contentDescription = category.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = category.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = category.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
