package com.unibucfmiifr2026.network.api

import com.unibucfmiifr2026.network.dto.UsersResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersApiService {
    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int): Response<UsersResponseDTO>
}


