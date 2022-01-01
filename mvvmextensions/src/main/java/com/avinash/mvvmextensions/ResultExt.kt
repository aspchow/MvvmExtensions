package com.avinash.mvvmextensions

suspend fun <T> Result<T>.handle(
    onResult: suspend () -> Unit = {},
    onSuccess: suspend (T) -> Unit,
    onFailure: suspend (Throwable) -> Unit
) {
    onResult()
    if (isSuccess) {
        onSuccess(getOrThrow())

    } else {
        onFailure(exceptionOrNull()!!)
    }
}
