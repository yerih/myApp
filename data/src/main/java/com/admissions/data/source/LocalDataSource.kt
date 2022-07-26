package com.admissions.data.source

import com.admissions.domain.User

interface LocalDataSource{

    suspend fun delete(user: User): Unit
    suspend fun insertOrReplace(user: User): Unit
    suspend fun getAll(): List<User>
    suspend fun getUserByEmail(email: String): User?
}