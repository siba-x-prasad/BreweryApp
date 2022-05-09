package com.fresco.beerbrewery.beer.ui.weigh

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.beer.model.Hop
import javax.inject.Inject

open class SharedBeerViewModel @Inject constructor() : ViewModel() {

    private val mutableBeerData = MutableLiveData<BeerItem>()
    private var _sharedBeerItem: BeerItem? = null
    fun getSharedBeerItem() = mutableBeerData

    fun updateBeerItem(beerItem: BeerItem?) {
        _sharedBeerItem = beerItem
        beerItem?.let {
            mutableBeerData.postValue(_sharedBeerItem!!)
        }
    }

    fun updateMaltsOrHop(maltOrHop: Hop?) {
        maltOrHop?.isWeighed = true
        mutableBeerData.value?.isWeighed = true
        if (maltOrHop?.attribute.isNullOrEmpty() && maltOrHop?.add.isNullOrEmpty()) {
            mutableBeerData.value?.ingredients?.hops?.set(maltOrHop?.id!!, maltOrHop)
        } else {
            mutableBeerData.value?.ingredients?.malt?.set(maltOrHop?.id!!, maltOrHop)
        }
    }
}
