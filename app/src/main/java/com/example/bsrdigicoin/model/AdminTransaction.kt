package com.example.bsrdigicoin.model

data class AdminTransaction(
    val stockName:String,
    val date: String,
    val time:String,
    val transactionType:String,
    val singleAmount:Double
)