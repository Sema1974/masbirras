package com.pdmjosemavidal.masbirras

 sealed class Routes(val route: String) {
     object Age_Verification:Routes("AgeVerificationScreen")
     object Underage:Routes("UnderageScreen")
     object Beer_List:Routes("BeerListScreen")
     object Beer_Detail:Routes("BeerDetailScreen/{beerId}"){
         fun createRoute(beerId: Int) = "BeerDetailScreen/$beerId"
     }
     object Favorites:Routes("FavoritesScreen")
     object Add_Beer:Routes("AddBeerScreen")
     object Remove_Beer:Routes("RemoveBeerScreen")
}