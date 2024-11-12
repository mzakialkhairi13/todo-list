package com.mzakialkhairi.techtest.data.model

data class ChecklistResponse(
    val `data`: DataX,
    val errorMessage: Any,
    val message: String,
    val statusCode: Int
)