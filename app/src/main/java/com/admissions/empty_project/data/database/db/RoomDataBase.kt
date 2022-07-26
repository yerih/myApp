package com.admissions.empty_project.data.database.db

import com.admissions.data.source.LocalDataSource
import com.admissions.domain.User
import com.admissions.empty_project.common.toDomain
import com.admissions.empty_project.common.toEntityDB
import com.admissions.empty_project.data.database.dao.UserDao
import javax.inject.Inject


class RoomDataBase @Inject constructor(
    private val userDao: UserDao
) : LocalDataSource {
    override suspend fun delete(user: User) = userDao.delete(user.toEntityDB())
    override suspend fun insertOrReplace(user: User) = userDao.insertAny(user.toEntityDB())
    override suspend fun getAll(): List<User> = userDao.getAll().toDomain()
    override suspend fun getUserByEmail(email: String): User? = userDao.getUserByEmail(email)?.toDomain()
}