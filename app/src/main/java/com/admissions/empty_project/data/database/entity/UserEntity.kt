package com.admissions.empty_project.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.admissions.empty_project.common.UserEntity
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = UserEntity)
data class UserEntity(
    @PrimaryKey

    val id: String,
    val email: String,
    val phone: String,
    val name: String,
    val image: String,
) : Parcelable

