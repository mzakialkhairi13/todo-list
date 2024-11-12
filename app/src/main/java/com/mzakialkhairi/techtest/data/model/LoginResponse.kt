package com.mzakialkhairi.techtest.data.model

data class LoginResponse(
    val `data`: Data,
    val errorMessage: Any,
    val message: String,
    val statusCode: Int
)