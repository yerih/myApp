package com.admissions.empty_project.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.admissions.domain.User
import com.admissions.empty_project.common.launch
import com.admissions.usecases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCases: UserUseCases,
): ViewModel() {
    fun start() {
        Log.i("TGB", "test")
    }

    init {
        launch(Dispatchers.IO) {
            Log.i("TGB", "inserting...")
            userUseCases.insertOrReplace(User())
            Log.i("TGB", "user = ${userUseCases.getAll()}")
        }
    }
}