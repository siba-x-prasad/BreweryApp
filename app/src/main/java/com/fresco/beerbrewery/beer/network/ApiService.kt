package com.fresco.beerbrewery.beer.network

import com.fresco.beerbrewery.beer.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("beers")
    suspend fun fetchBeerByPage(
        @Query("page") page: Int,
        @Query("per_page") itemCount: Int
    ): Response<BeerResponse>
}