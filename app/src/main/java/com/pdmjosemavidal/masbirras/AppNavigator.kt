package com.pdmjosemavidal.masbirras

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pdmjosemavidal.masbirras.model.BeerViewModel


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigator() {
    val viewModel: BeerViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.AGE_VERIFICATION,
        modifier = Modifier
    ) {
        composable(Routes.AGE_VERIFICATION) {
            AgeVerificationScreen(navController)
        }
        composable(Routes.UNDERAGE) {
            UnderageScreen(navController)
        }
        composable(Routes.BEER_LIST) {
            BeerListScreen(navController, viewModel)
        }
        composable(Routes.BEER_DETAIL) { backStackEntry ->
            val beerId = backStackEntry.arguments?.getString("beerId")?.toIntOrNull()
            if (beerId != null) {
                BeerDetailScreen(navController, beerId, viewModel)
            }
        }
        composable("favorites") {
            FavoritesScreen(navController, viewModel)
        }
        composable(Routes.ADD_BEER) {
            AddBeerScreen(navController, viewModel)
        }
        composable(Routes.REMOVE_BEER) {
            RemoveBeerScreen(navController, viewModel)
        }
    }
}
