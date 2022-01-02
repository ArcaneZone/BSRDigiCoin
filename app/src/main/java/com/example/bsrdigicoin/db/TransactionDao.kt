package com.example.bsrdigicoin.db

import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    fun getAll(): List<Transaction>

    @Insert
     fun insert(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Query("UPDATE transaction_table SET status='A' where transactionId=:transactionId")
    fun approveStatus(transactionId: Int)

    @Query("UPDATE transaction_table SET status='D' where transactionId=:transactionId")
    fun disapproveStatus(transactionId: Int)

    @Query("SELECT COUNT(*) FROM transaction_table where status='F'")
    fun getReviewToCheck(): Int

    @Query("SELECT * FROM transaction_table order by stock_date,stock_time LIMIT 3")
    fun getLastest3():List<Transaction>
}