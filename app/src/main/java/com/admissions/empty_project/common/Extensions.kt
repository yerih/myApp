package com.admissions.empty_project.common

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.lifecycle.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.admissions.domain.User
import com.admissions.empty_project.ui.AdapterUser
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


inline fun <T> basicDiffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
        areContentsTheSame(oldItem, newItem)
}


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)


@BindingAdapter("items")
fun RecyclerView.setItems(list: List<User>?){
    list?.let { (adapter as? AdapterUser)?.submitList(list) }
}
