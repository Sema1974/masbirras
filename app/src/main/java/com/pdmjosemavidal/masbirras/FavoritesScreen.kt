package com.pdmjosemavidal.masbirras

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmjosemavidal.masbirras.model.BeerViewModel

@Composable
fun FavoritesScreen(navController: NavController, viewModel: BeerViewModel) {
    val context = LocalContext.current
    val favoriteBeers by remember { mutableStateOf(viewModel.getFavorites()) }

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Cervezas Favoritas",
                showMenuIcon = false,
                onAddClick = { navController.navigate(Routes.Add_Beer.route) },
                onDeleteClick = { navController.navigate(Routes.Remove_Beer.route) }
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteBeers) { beer ->
                    BeerRow(navController, beer)
                }
            }
        },
        bottomBar = {
            BottomBarNavigation(navController, onExit = {
                (context as? MainActivity)?.finish()
            })
        }
    )
}