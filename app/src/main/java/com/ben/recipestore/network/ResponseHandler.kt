package com.ben.recipestore.network

import okhttp3.ResponseBody

data class ResponseHandler<T>(val status: Status, val data: T?, val message: String?, val responseBody: ResponseBody? = null) {

    companion object {
        fun <T> onSuccess(data: T?): ResponseHandler<T> = ResponseHandler(Status.SUCCESS, data, null)
        fun <T> onFailure(message: String?, data: T?, responseBody: ResponseBody? = null): ResponseHandler<T> = ResponseHandler(Status.FAILURE, data, message, responseBody)
        fun <T> onLoading(message: String?): ResponseHandler<T> = ResponseHandler(Status.LOADING, null, message)
    }
}