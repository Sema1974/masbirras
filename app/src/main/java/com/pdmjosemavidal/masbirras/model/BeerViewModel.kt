package com.pdmjosemavidal.masbirras.model

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import com.pdmjosemavidal.masbirras.R

class BeerViewModel : ViewModel() {
    private val _beers = mutableStateListOf<Beer>()
    val beers: List<Beer> get() = _beers

    init {
        loadBeers()
    }

    private fun loadBeers() {
        _beers.addAll(SampleData.beers)
    }

    fun toggleFavorite(beerId: Int) {
        val index = _beers.indexOfFirst { it.id == beerId }
        if (index != -1) {
            _beers[index] = _beers[index].copy(isFavorite = !_beers[index].isFavorite)
        }
    }

    fun getFavorites(): List<Beer> {
        return beers.filter { it.isFavorite }
    }

    fun addBeer(name: String, type: String, country: String, alcohol: Double, description: String) {

        val newBeer = Beer(
            id = _beers.size + 1,
            name = name,
            type = type,
            degrees = alcohol,
            country = country,
            description = description,
            imageUrl = R.drawable.jarras,
            isFavorite = false
        )
        _beers.add(newBeer)
    }

    fun removeBeerByName(name: String) {
        _beers.removeAll { it.name.equals(name, ignoreCase = true) }
    }
}


