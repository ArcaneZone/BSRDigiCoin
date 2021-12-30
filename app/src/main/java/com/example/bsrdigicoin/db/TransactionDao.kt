package com.example.bsrdigicoin.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    fun getAll(): List<Transaction>

    @Query("SELECT * FROM transaction_table WHERE user_id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Transaction>

    @Insert
    fun insertAll(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

}