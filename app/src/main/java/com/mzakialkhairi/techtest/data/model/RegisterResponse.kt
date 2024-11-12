package com.mzakialkhairi.techtest.data.model

data class RegisterResponse(
    val `data`: Any,
    val errorMessage: Any,
    val message: String,
    val statusCode: Int
)