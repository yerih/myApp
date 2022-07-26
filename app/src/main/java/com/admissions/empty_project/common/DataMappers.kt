package com.admissions.empty_project.common

import com.admissions.domain.User
import com.admissions.empty_project.data.database.entity.UserEntity

fun User.toEntityDB(): UserEntity = UserEntity(id, email, phone, name, image)
fun UserEntity.toDomain(): User = User(id, email, phone, name, image)
fun List<UserEntity>.toDomain() = map { it.toDomain() }

