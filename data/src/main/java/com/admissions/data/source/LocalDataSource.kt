package com.admissions.data.source

interface LocalDataSource{

    suspend fun method(): Unit
}