package com.admissions.usecases

import com.admissions.data.repository.UserRepository
import com.admissions.domain.User


class UserUseCases(
    private val userRepository: UserRepository
){
    suspend fun getAll() = userRepository.getAll()
    suspend fun insertOrReplace(user: User) = userRepository.insertOrReplaceUser(user)
    suspend fun delete(user: User) = userRepository.delete(user)
    suspend fun isEmpty() = userRepository.isEmpty()
    suspend fun getUserByEmail(email: String) = userRepository.getUserByEmail(email)
}