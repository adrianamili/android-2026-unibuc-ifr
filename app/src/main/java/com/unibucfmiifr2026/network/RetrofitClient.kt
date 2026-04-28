package com.unibucfmiifr2026.network

import com.unibucfmiifr2026.network.api.UsersApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://reqres.in/"

    private const val API_KEY = "reqres_d749c612b8334679b7a9da1822ea4c11"

    private val logInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val apiKeyInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("x-api-key", API_KEY)
            .build()
        chain.proceed(request)
    }
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor (logInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()

    val api : UsersApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApiService::class.java)

    }
}