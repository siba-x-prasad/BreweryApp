package com.fresco.beerbrewery.beer.ui.weigh

import android.widget.SeekBar
import androidx.databinding.ObservableField
import com.fresco.beerbrewery.beer.model.Hop
import com.fresco.beerbrewery.beer.model.WeighUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeerWeighViewModel @Inject constructor() : SharedBeerViewModel() {

    var weighDetails: Hop? = null
    val weighUiState = WeighUiState()

    var targetProgress = ObservableField<Int>(0)

    init {
        targetProgress.set(weighDetails?.amount?.value?.toInt())
        weighUiState.seekBarValue.set(25)
    }

    fun onSeekBarChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        targetProgress.set(progress)
    }
}

