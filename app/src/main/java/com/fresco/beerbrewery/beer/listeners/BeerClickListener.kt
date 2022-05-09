package com.fresco.beerbrewery.beer.listeners

import com.fresco.beerbrewery.beer.model.BeerItem

interface BeerClickListener {
    fun onBeerClick(beerItem: BeerItem)
}