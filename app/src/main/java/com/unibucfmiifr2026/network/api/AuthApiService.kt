package com.unibucfmiifr2026.network.api

import com.unibucfmiifr2026.network.dto.LoginRequestDTO
import com.unibucfmiifr2026.network.dto.LoginResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/login")
    suspend fun login(@Body request: LoginRequestDTO): LoginResponseDTO


}