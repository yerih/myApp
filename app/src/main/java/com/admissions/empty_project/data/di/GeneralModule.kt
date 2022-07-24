package com.admissions.empty_project.data.di

import com.admissions.data.repository.AnyRepository
import com.admissions.usecases.AnyUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class GeneralModule {

    @Provides
    fun anyUseCasesProvider(anyRepository: AnyRepository) = AnyUseCases(anyRepository)
}