package com.pdmjosemavidal.masbirras

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmjosemavidal.masbirras.model.Beer
import com.pdmjosemavidal.masbirras.model.BeerViewModel
import kotlinx.coroutines.launch

@Composable
fun BeerListScreen(navController: NavController, viewModel: BeerViewModel) {
    val beers by remember { mutableStateOf(viewModel.beers) }
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current

    var filteredBeers by remember { mutableStateOf(beers) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                val beerTypes = beers.distinctBy { it.type }.map { it.type }
                LazyColumn {
                    items(beerTypes) { type ->
                        Text(
                            text = type,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    scope.launch { drawerState.close() }
                                    filteredBeers = beers.filter { it.type == type  }
                                }
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    title = "Listado de Cervezas",
                    showMenuIcon = true,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onAddClick = { navController.navigate(Routes.ADD_BEER) },
                    onDeleteClick = { navController.navigate(Routes.REMOVE_BEER) }
                )
            },
            content = { padding ->
                LazyColumn(
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filteredBeers) { beer ->
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
}

@Composable
fun BeerRow(navController: NavController, beer: Beer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("beer_detail/${beer.id}") }
            .padding(8.dp),
            elevation = CardDefaults.cardElevation(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = beer.imageUrl),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = beer.name, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


























