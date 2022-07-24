package com.admissions.empty_project.data.di

import com.admissions.data.repository.AnyRepository
import com.admissions.data.source.AnyRemoteDataSource
import com.admissions.empty_project.data.database.db.RoomDataBase
import com.admissions.empty_project.data.server.AnyNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun anyRepositoryProvider(anyNetworkDataSource: AnyNetworkDataSource, localDataSource: RoomDataBase) = AnyRepository(anyNetworkDataSource,localDataSource)

}