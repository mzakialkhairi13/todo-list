package com.mzakialkhairi.techtest.data.network

import com.mzakialkhairi.techtest.data.model.LoginData
import com.mzakialkhairi.techtest.data.model.LoginResponse
import com.mzakialkhairi.techtest.data.model.RegisterData
import com.mzakialkhairi.techtest.data.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterData): Response<RegisterResponse>
    @POST("/login")
    suspend fun login(@Body loginRequest: LoginData): Response<LoginResponse>
    @POST("/checklist")
    suspend fun checklist(@Body loginRequest: LoginData): Response<LoginResponse>


}