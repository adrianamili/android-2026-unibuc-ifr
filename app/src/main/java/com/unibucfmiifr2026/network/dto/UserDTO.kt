package com.unibucfmiifr2026.network.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: Long,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)

data class UsersResponseDTO(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<UserDTO>
)

