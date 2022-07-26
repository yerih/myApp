package com.admissions.empty_project.data.database.dao

import androidx.room.*
import com.admissions.empty_project.common.UserEntityName
import com.admissions.empty_project.data.database.entity.UserEntity


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAny(p: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("SELECT * FROM $UserEntityName")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM $UserEntityName where email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?


}

