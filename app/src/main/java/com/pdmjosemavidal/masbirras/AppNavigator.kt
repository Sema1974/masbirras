package com.pdmjosemavidal.masbirras

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pdmjosemavidal.masbirras.model.BeerViewModel
import com.pdmjosemavidal.masbirras.Routes

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigator() {
    val viewModel: BeerViewModel = viewModel()
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Age_Verification.route,
        modifier = Modifier
    ) {
        composable(Routes.Age_Verification.route) {
            AgeVerificationScreen(navController)
        }
        composable(Routes.Underage.route) {
            UnderageScreen(navController)
        }
        composable(Routes.Beer_List.route) {
            BeerListScreen(navController, viewModel)
        }
        composable(Routes.Beer_Detail.route, arguments = listOf(navArgument("beerId"){type =
            NavType.IntType })) { backStackEntry ->
            val beerId = backStackEntry.arguments?.getInt("beerId")
            if (beerId != null) {
                BeerDetailScreen(navController, beerId, viewModel)
            }else {
                Text("Error: ID de la cerveza no válido")
            }
        }
        composable(Routes.Favorites.route) {
            FavoritesScreen(navController, viewModel)
        }
        composable(Routes.Add_Beer.route) {
            AddBeerScreen(navController, viewModel)
        }
        composable(Routes.Remove_Beer.route) {
            RemoveBeerScreen(navController, viewModel)
        }
    }
}
