package com.avinash.mvvmextensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 *
 * creates a coroutine on IO thread with lifecycle scope
 *
 * */



fun CoroutineScope.launchOnIO(block: suspend () -> Unit) = launch(Dispatchers.IO) {
    block()
}

suspend fun onMainContext(block: suspend () -> Unit) = withContext(Dispatchers.Main){
    block()
}


suspend fun <T> Flow<T>.collectOnMain(block: suspend (T) -> Unit) = collect {
   onMainContext {
       block(it)
   }
}


suspend fun <T> Flow<T>.collectLatestOnMain(block: suspend (T) -> Unit) = collectLatest {
  onMainContext {
        block(it)
    }
}

