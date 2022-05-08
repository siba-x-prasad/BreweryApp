package com.fresco.beerbrewery.beer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fresco.beerbrewery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beer)
    }
}