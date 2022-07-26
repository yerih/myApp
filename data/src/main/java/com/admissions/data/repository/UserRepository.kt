package com.admissions.data.repository

import com.admissions.data.source.AnyRemoteDataSource
import com.admissions.data.source.LocalDataSource
import com.admissions.domain.User

class UserRepository(
    private val anyRemoteDataSource: AnyRemoteDataSource,
    private val localDataSource: LocalDataSource
){
    suspend fun insertOrReplaceUser(user: User) = localDataSource.insertOrReplace(user)
    suspend fun delete(user: User) = localDataSource.delete(user)
    suspend fun getAll() = localDataSource.getAll()
    suspend fun isEmpty() = localDataSource.getAll().isEmpty()
    suspend fun getUserByEmail(email: String) = localDataSource.getUserByEmail(email)
}