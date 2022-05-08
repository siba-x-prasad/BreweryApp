package com.fresco.beerbrewery.beer.ui.base

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel(), Observable {

    var obsevableLoading = ObservableBoolean(false)
    var loadMore = ObservableBoolean(false)

    var errorMessage = ObservableField("")


    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}