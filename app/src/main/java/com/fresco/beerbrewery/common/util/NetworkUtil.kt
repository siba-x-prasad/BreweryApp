package com.fresco.beerbrewery.common.util

import android.content.Context
import android.net.ConnectivityManager

object NetworkUtil {
    fun isAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}

