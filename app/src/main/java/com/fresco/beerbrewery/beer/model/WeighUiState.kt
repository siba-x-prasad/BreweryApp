package com.fresco.beerbrewery.beer.model

import android.widget.SeekBar
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class WeighUiState : BaseObservable() {

    var seekBarValue = ObservableField(0)

    val seekbarChangeListener = object :
        SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(
            seek: SeekBar,
            progress: Int, fromUser: Boolean
        ) {
            seekBarValue.set(progress)
        }

        override fun onStartTrackingTouch(seek: SeekBar) {
        }

        override fun onStopTrackingTouch(seek: SeekBar) {

        }
    }
}