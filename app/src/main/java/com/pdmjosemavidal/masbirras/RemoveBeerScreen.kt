package com.pdmjosemavidal.masbirras

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pdmjosemavidal.masbirras.model.BeerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemoveBeerScreen(navController: NavController, viewModel: BeerViewModel) {
    var beerName by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Eliminar Cerveza") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = beerName,
                onValueChange = { beerName = it },
                label = { Text("Nombre de la Cerveza") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.removeBeerByName(beerName)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar Cerveza")
            }
        }
    }
}
