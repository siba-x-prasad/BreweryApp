package com.fresco.beerbrewery.beer.ui.beerList

import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.fresco.beerbrewery.beer.model.BeerItem
import com.fresco.beerbrewery.beer.model.BeerResponse
import com.fresco.beerbrewery.beer.model.ResponseState
import com.fresco.beerbrewery.beer.ui.base.BaseViewModel
import com.fresco.beerbrewery.common.util.Constants
import com.fresco.beerbrewery.common.util.NetworkUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerListViewModel @Inject constructor(
    @ApplicationContext private val context: Context?,
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    private var beerList: List<BeerItem> = listOf()
    private var pageCount: Int = Constants.DEFAULT_PAGE_NUMBER
    private var itemCountPerPage: Int = Constants.DEFAULT_ITEM_PER_PAGE

    init {
        loadBeerData()
    }

    val beerData: LiveData<List<BeerItem>>
        get() = _beerData
    private val _beerData = MutableLiveData<List<BeerItem>>(emptyList())

    fun loadBeerData() {
        viewModelScope.launch {
            if (NetworkUtil.isAvailable(context!!)) {
                try {
                    obsevableLoading.set(true)
                    weatherRepository.fetchBeersByPage(pageCount, itemCountPerPage).let {
                        obsevableLoading.set(false)
                        if (it.isSuccessful) {
                            val beerList = it.body()?.toList()
                            beerList?.let { beers ->
                                _beerData.postValue(beers)
                            }
                            pageCount++
                        } else {
                            errorMessage.set("Beers Not found")
                        }
                    }

                } catch (e: Exception) {
                    obsevableLoading.set(false)
                    errorMessage.set("Fetching Error ${e.message}")
                }
            } else {
                obsevableLoading.set(false)
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fetchBeerList(context: Context, itemCount: Int) {
        try {
            if (NetworkUtil.isAvailable(context)) {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        weatherRepository.fetchBeersByPage(pageCount, itemCountPerPage).let {
                            pageCount++
                        }
                    } catch (e: Exception) {

                    }
                }
            } else {
                Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show()
            }
        } catch (exception: Exception) {

        }
    }

    fun getBeerLists() = liveData(Dispatchers.IO) {
        if (beerList.isEmpty()) {
            obsevableLoading.set(true)
            try {
                val listMeteors = weatherRepository.fetchBeersByPage(pageCount, itemCountPerPage)
                emit(ResponseState.success(beerList))
                obsevableLoading.set(false)
            } catch (exception: Exception) {
                obsevableLoading.set(false)
                errorMessage.set(exception.message ?: "Error Occurred!")
                emit(ResponseState.error(null, exception.message ?: "Error Occurred!"))
            }
        } else {
            obsevableLoading.set(false)
            emit(ResponseState.success(beerList))
        }
    }
}