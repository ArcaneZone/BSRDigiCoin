package com.example.bsrdigicoin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
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
    val TotalValue: Double,
    @ColumnInfo(name = "userTotalCount")
    val userTotalCount: Int
)