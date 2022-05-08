@file:JvmName("Logger")

package com.fresco.beerbrewery.common.util

import android.util.Log
import com.fresco.beerbrewery.BuildConfig

fun String.logD(any: Any) {
    logD(any::class.java.simpleName)
}

fun String.logD(tag: String) {
    debugLogs(tag, this)
}

private fun debugLogs(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(tag, message)
    }
}

fun String.logE(any: Any) {
    logE(any::class.java.simpleName)
}

fun String.logE(tag: String) {
    errorLogs(tag, this)
}

private fun errorLogs(tag: String, message: String) {
    Log.e(tag, message)
}