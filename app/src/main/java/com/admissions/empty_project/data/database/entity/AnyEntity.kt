package com.admissions.empty_project.data.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.admissions.empty_project.common.AnyEntityName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = AnyEntityName)
data class AnyEntity(
    @PrimaryKey
    val id: String,
    val any: Boolean
) : Parcelable