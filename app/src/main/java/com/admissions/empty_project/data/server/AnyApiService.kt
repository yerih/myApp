package com.admissions.empty_project.data.server

interface AnyApiService{

}

object AnyApi{
    val retrofitAppService: AnyApiService by lazy {
        RetrofitSettings.builder("").create(AnyApiService::class.java)
    }
}

