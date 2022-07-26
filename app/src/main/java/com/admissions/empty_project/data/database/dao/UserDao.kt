package com.admissions.empty_project.data.database.dao

import androidx.room.*
import com.admissions.empty_project.common.UserEntity
import com.admissions.empty_project.data.database.entity.UserEntity


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAny(p: UserEntity)

    @Query("DELETE FROM $UserEntity WHERE id = :id")
    fun deleteById(id: Long)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("SELECT * FROM $UserEntity")
    fun getAll(): List<UserEntity>

//    @Query("SELECT * FROM $AnyEntityName where id=:id")
//    suspend fun getAnyById(id: String): AnyEntity?


}

