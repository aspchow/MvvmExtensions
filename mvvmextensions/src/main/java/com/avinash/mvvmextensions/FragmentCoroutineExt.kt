package com.avinash.mvvmextensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


fun Fragment.launchOnIO(block : suspend () -> Unit){
    lifecycleScope.launchOnIO {
        repeatOnLifecycle(Lifecycle.State.RESUMED){
            block()
        }
    }
}

fun <T> Fragment.collectFlowOnMain(flow: Flow<T>, collect: suspend (T) -> Unit) =
    lifecycleScope.launch(Dispatchers.IO) {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectOnMain {
                collect(it)
            }
        }
    }


fun <T> Fragment.collectLatestFlowOnMain(flow: Flow<T>, collect: suspend (T) -> Unit) =
    lifecycleScope.launch(Dispatchers.IO) {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatestOnMain {
                collect(it)
            }
        }
    }


fun <T> Fragment.collectFlow(flow: Flow<T>, collect: suspend (T) -> Unit) =
    lifecycleScope.launch(Dispatchers.IO) {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                collect(it)
            }
        }
    }


fun <T> Fragment.collectLatestFlow(flow: Flow<T>, collect: suspend (T) -> Unit) =
    lifecycleScope.launch(Dispatchers.IO) {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest {
                collect(it)
            }
        }
    }


