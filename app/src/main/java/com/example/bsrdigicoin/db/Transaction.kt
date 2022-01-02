package com.example.bsrdigicoin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "transactionId")
    val transactionId:Int,
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "stock_name")
    val stockName: String,
    @ColumnInfo(name = "no_of_stock")
    val noOfStock: Int,
    @ColumnInfo(name = "stock_date")
    val stockDate: String,
    @ColumnInfo(name = "stock_time")
    val stockTime: String,
    @ColumnInfo(name="stock_single_price")
    val stockSinglePrice:Double,
    @ColumnInfo(name="stock_total_price")
    val stockTotalPrice:Double,
    @ColumnInfo(name="user_id")
    val UserId:Int,
    @ColumnInfo(name="stock_transaction_type")
    val stockTransactionType:String,
    @ColumnInfo(name="status")
    val status:String
)
