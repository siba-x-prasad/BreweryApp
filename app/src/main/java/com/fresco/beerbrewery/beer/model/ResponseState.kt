package com.fresco.beerbrewery.beer.model

data class ResponseState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): ResponseState<T> =
            ResponseState(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): ResponseState<T> =
            ResponseState(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): ResponseState<T> =
            ResponseState(status = Status.LOADING, data = data, message = null)
    }
}

