package com.admissions.empty_project.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.admissions.empty_project.common.AnyEntityName
import com.admissions.empty_project.data.database.entity.AnyEntity


@Dao
interface AnyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAny(p: AnyEntity)

//    @Delete
//    fun deleteAny()

    @Query("SELECT * FROM $AnyEntityName")
    fun getAll(): List<AnyEntity>

//    @Query("SELECT * FROM $AnyEntityName where id=:id")
//    suspend fun getAnyById(id: String): AnyEntity?


}

