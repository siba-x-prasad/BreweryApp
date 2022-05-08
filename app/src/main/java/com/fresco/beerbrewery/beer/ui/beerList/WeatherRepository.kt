package com.fresco.beerbrewery.beer.ui.beerList

import com.fresco.beerbrewery.beer.network.ApiHelperImpl
import javax.inject.Inject


/**
 * Created by Sibaprasad Mohanty on 06/05/2022.
 * siba.x.prasad@gmail.com
 */

class WeatherRepository @Inject constructor(private val apiHelper: ApiHelperImpl) {
    suspend fun fetchBeersByPage(page: Int, itemCount: Int) =
        apiHelper.fetchBeerByPage(page, itemCount)
}