package com.admissions.empty_project.ui

import androidx.lifecycle.ViewModel
import com.admissions.usecases.AnyUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val anyUseCases: AnyUseCases
): ViewModel() {

}