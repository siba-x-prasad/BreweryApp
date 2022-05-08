package com.fresco.beerbrewery.beer.listeners

import com.fresco.beerbrewery.beer.model.Hop

interface IngredientClickListener {
    fun onWeighClick(hopMalts: Hop)
}