package com.fresco.beerbrewery.beer.ui.beerList

import com.fresco.beerbrewery.beer.network.ApiHelperImpl
import javax.inject.Inject

class BearListRepository @Inject constructor(private val apiHelper: ApiHelperImpl) {
    suspend fun fetchBeersByPage(page: Int, itemCount: Int) =
        apiHelper.fetchBeerByPage(page, itemCount)
}