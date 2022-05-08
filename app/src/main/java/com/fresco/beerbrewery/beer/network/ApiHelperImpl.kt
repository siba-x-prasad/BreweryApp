package com.fresco.beerbrewery.beer.network

import com.fresco.beerbrewery.beer.model.BeerResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun fetchBeerByPage(
        page: Int,
        itemPerPage: Int
    ): Response<BeerResponse> = apiService.fetchBeerByPage(page, itemPerPage)

}