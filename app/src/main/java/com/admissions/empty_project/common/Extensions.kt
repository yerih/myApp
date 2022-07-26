package com.admissions.empty_project.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launch(dispatcher: CoroutineDispatcher = Dispatchers.Default, run: suspend ()->Unit) = viewModelScope.launch(dispatcher){ run()}