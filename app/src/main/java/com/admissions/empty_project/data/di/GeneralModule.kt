package com.admissions.empty_project.data.di

import com.admissions.data.repository.UserRepository
import com.admissions.usecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class GeneralModule {

    @Provides
    fun anyUseCasesProvider(userRepository: UserRepository) = UserUseCases(userRepository)
}