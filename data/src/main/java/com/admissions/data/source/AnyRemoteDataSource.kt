package com.admissions.data.source

interface AnyRemoteDataSource {
    suspend fun remoteMethod(): Unit
}