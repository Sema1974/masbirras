package com.pdmjosemavidal.masbirras.model

import androidx.annotation.DrawableRes

data class Beer(
    val id: Int,
    val name: String,
    val type: String,
    val degrees: Double,
    val country: String,
    val description: String,
    @DrawableRes var imageUrl: Int,
    var isFavorite: Boolean = false
)
