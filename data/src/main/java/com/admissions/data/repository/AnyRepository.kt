package com.admissions.data.repository

import com.admissions.data.source.AnyRemoteDataSource
import com.admissions.data.source.LocalDataSource

class AnyRepository(
    private val anyRemoteDataSource: AnyRemoteDataSource,
    private val localDataSource: LocalDataSource
){
//    suspend fun anyRemote(): Unit = anyRemoteDataSource.remoteMethod()
    suspend fun anyDB(): Unit = localDataSource.method()
}