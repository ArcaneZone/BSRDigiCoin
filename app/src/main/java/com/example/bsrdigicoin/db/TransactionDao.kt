package com.example.bsrdigicoin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    fun getAll(): List<Transaction>

    @Insert
     fun insert(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Query("SELECT * FROM transaction_table where user_id=:id")
    fun getTransactionbyId(id:Int): List<Transaction>

    @Query("SELECT * FROM transaction_table WHERE status=:stat LIMIT 3 ")
    fun getTransactionForReview(stat:Char): List<Transaction>
}