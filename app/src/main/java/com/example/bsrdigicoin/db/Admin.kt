package com.example.bsrdigicoin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Admin(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "userId")
    val userId:Int,
    @ColumnInfo(name = "username",)
    val userName: String,
    @ColumnInfo(name = "userFullName")
    val fullName: String,
    @ColumnInfo(name = "userEmail")
    val userEmail: String,
    @ColumnInfo(name = "userPassword")
    val userPassWord: String,
    @ColumnInfo(name = "userBalance")
    val TotalBalance: Double,
    @ColumnInfo(name = "userTotalValueInvested")
    val userTotalValueInvested: Double,
    @ColumnInfo(name = "userTotalValue")
    val userTotalValue: Double
)