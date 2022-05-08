package com.fresco.beerbrewery

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BeerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}