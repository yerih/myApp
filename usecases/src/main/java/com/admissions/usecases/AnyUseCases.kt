package com.admissions.usecases

import com.admissions.data.repository.AnyRepository


class AnyUseCases(
    private val anyRepository: AnyRepository
){
//    suspend fun anyRemote() = anyRepository.anyRemote()
    suspend fun anyLocal() = anyRepository.anyDB()
}