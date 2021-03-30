package com.ben.recipestore.repository

import android.util.Log
import com.ben.recipestore.network.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

abstract class BaseRepository {

    suspend fun <T> callHandler(call: suspend () -> T): ResponseHandler<T> =
            withContext(Dispatchers.IO) {
                try {
                    ResponseHandler.onSuccess(call.invoke())
                } catch (throwable: Throwable) {
                    when(throwable) {
                        is HttpException -> {
                            ResponseHandler.onFailure(throwable.message(), null, throwable.response()?.errorBody())
                        }
                        else -> {
                            ResponseHandler.onFailure(throwable.message, null,)
                        }
                    }
                }
            }
}