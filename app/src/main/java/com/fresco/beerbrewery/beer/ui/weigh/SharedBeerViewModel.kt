package com.fresco.beerbrewery.beer.ui.weigh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fresco.beerbrewery.beer.model.BeerItem
import javax.inject.Inject

class SharedBeerViewModel @Inject constructor() : ViewModel() {

    var mutableBeerData = MutableLiveData<BeerItem>()

    fun updateBeerItem(beerItem: BeerItem?) {
        beerItem?.let {
            mutableBeerData.postValue(it)
        }
    }
}
