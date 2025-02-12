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
fun AddBeerScreen(navController: NavController, viewModel: BeerViewModel) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var alcohol by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Añadir Cerveza") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
            OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Tipo") })
            OutlinedTextField(value = country, onValueChange = { country = it }, label = { Text("País") })
            OutlinedTextField(value = alcohol, onValueChange = { alcohol = it }, label = { Text("Graduación %") })
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.addBeer(name, type, country, alcohol.toDoubleOrNull() ?: 0.0, description)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Añadir Cerveza")
            }
        }
    }
}
