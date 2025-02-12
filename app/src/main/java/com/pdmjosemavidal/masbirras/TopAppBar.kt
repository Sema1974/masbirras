package com.pdmjosemavidal.masbirras

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String,
    showMenuIcon: Boolean = true,
    onMenuClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
    )
    {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = {
                if (showMenuIcon) {
                    IconButton(onClick = onMenuClick) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menú")
                    }
                }
            },
            actions = {
                IconButton(onClick = onAddClick) { Icon(Icons.Filled.Add, contentDescription = "añadir") }
                IconButton(onClick = onDeleteClick) { Icon(Icons.Filled.Close, contentDescription = "borrar") }
            }
        )
    }