package com.example.bsrdigicoin.model

import java.util.*

data class UserTransaction(
    val count:Int,
    val stockName:String,
    val date: String,
    val time:String,
    val transactionType:String,
    val TotalAmount:Double,
    val singleAmount:Double
)
