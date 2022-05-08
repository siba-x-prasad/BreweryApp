package com.fresco.beerbrewery.beer.listeners

import com.fresco.beerbrewery.beer.model.BeerItem


/**
 * Created by Sibaprasad Mohanty on 07/05/2022.
 * siba.x.prasad@gmail.com
 */

interface BeerClickListener {
    fun onBeerClick(beerItem: BeerItem)
}