package com.pdmjosemavidal.masbirras

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment


@Composable
fun AgeVerificationScreen(navController: NavController) {
    var yearOfBirth by remember { mutableStateOf("") }
    val isValidYear = yearOfBirth.toIntOrNull()?.let { it <= 2006 } ?: false

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.jarras),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(text = "Fecha de Nacimiento", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = yearOfBirth,
            onValueChange = { yearOfBirth = it },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color(0xFFB2B2B2),
                unfocusedTextColor = Color(0xFFB2B2B2),
                focusedContainerColor = Color(0xFFFAFAFA),
                unfocusedContainerColor = Color(0xFFFAFAFA),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            placeholder = { Text(text = "Año de Nacimiento") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (isValidYear) {
                navController.navigate(Routes.Beer_List.route)
            } else {
                navController.navigate(Routes.Underage.route)
            }
        }) {
            Text(text = "Entrar")
        }
    }
}
