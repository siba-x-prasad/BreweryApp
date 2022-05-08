package com.fresco.beerbrewery.beer.network

import com.fresco.beerbrewery.beer.model.BeerResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun fetchBeerByPage(pageNumber: Int, itemParePage: Int): Response<BeerResponse>
}