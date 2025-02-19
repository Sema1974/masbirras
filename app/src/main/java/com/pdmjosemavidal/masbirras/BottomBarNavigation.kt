package com.pdmjosemavidal.masbirras

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun BottomBarNavigation(navController: NavController, onExit: () -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Routes.Beer_List.route) },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate(Routes.Favorites.route) },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favoritos") },
            label = { Text("FaV") }
        )
        NavigationBarItem(
            selected = false,
            onClick = onExit,
            icon = { Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Salir") },
            label = { Text("Salir") }
        )
    }
}
