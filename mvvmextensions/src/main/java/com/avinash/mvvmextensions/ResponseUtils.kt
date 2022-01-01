package com.avinash.mvvmextensions

import okhttp3.ResponseBody
import retrofit2.Response


suspend fun <T> Response<T>.handle(
    onResult: () -> Unit = {},
    onSuccess: suspend (T, Int) -> Unit,
    onFailure: suspend (ResponseBody, Int) -> Unit
) {

    onResult()

    if (isSuccessful) {
        onSuccess(body()!!, code())
    } else {
        onFailure(errorBody()!!, code())
    }
}


suspend fun <T> Response<T>.handle(
    onResult: () -> Unit = {},
    onSuccess: suspend (T) -> Unit,
    onFailure: suspend (ResponseBody) -> Unit
) {

    onResult()

    if (isSuccessful) {
        onSuccess(body()!!)
    } else {
        onFailure(errorBody()!!)
    }
}