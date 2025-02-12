package com.pdmjosemavidal.masbirras

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmjosemavidal.masbirras.model.BeerViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeerDetailScreen(navController: NavController, beerId: Int, viewModel: BeerViewModel) {

    val beer = viewModel.beers.find { it.id == beerId } ?: return
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(beer.name) })
        },
        bottomBar = {
            BottomBarNavigation(navController, onExit = {
                (context as? MainActivity)?.finish()
            })
        },
        content = { padding ->
            DetailContent(beerId, viewModel, padding)
        }
    )
}

@Composable
fun DetailContent(beerId: Int, viewModel: BeerViewModel, padding: PaddingValues) {
    val beer = viewModel.beers.find { it.id == beerId } ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
            .background(Color(0xFFFAFAFA))
    ) {
        Image(
            painter = painterResource(id = beer.imageUrl),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = beer.name, style = MaterialTheme.typography.headlineMedium)
        Text(text = "Tipo: ${beer.type}", style = MaterialTheme.typography.titleMedium)
        Text(text = "Grados: ${beer.degrees}", style = MaterialTheme.typography.titleMedium)
        Text(text = "País: ${beer.country}", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = beer.description, style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.toggleFavorite(beerId) }) {
            Text(if (beer.isFavorite) "Quitar de Favoritos" else "Añadir a Favoritos")
        }
    }
}
