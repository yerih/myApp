package com.admissions.empty_project.common

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun Any.log(msg: String, label: String = "TGB"){
    Log.i(label, msg)
}

fun ViewModel.launch(dispatcher: CoroutineDispatcher = Dispatchers.Default, run: suspend ()->Unit) = viewModelScope.launch(dispatcher){ run()}

fun <T> LifecycleOwner.launchAndCollect(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
){
    lifecycleScope.launch {
        this@launchAndCollect.repeatOnLifecycle(state){
            flow.collect(body)
        }
    }
}