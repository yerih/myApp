package com.admissions.empty_project.common

import com.admissions.domain.User
import com.admissions.empty_project.data.database.entity.UserEntity

fun User.toEntityDB(): UserEntity = UserEntity(email = email, phone = phone, name = name, image = image)
fun UserEntity.toDomain(): User = User(email = email, phone = phone, name = name, image = image)
fun List<UserEntity>.toDomain() = map { it.toDomain() }

