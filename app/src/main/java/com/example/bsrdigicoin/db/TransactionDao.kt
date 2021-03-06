package com.example.bsrdigicoin.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_table")
    fun getAll(): LiveData<List<Transaction>>

    @Insert
     fun insert(transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Query("UPDATE transaction_table SET status='A' where transactionId=:transactionId")
    fun approveStatus(transactionId: Int)

    @Query("DELETE FROM TRANSACTION_TABLE WHERE transactionId=:transactionId")
    fun disapproveStatus(transactionId: Int)

    @Query("SELECT COUNT(*) FROM transaction_table where status='F'")
    fun getReviewToCheck(): Int

    @Query("SELECT * FROM transaction_table where status='A' order by transactionId desc LIMIT 3")
    fun getLastest3():List<Transaction>

    @Query("SELECT * FROM transaction_table where status='F'")
    fun getAllForReview(): LiveData<MutableList<Transaction>>

    @Query("SELECT * FROM transaction_table where status ='A' AND user_id=:userid")
    fun getTransactionsById(userid:Int): LiveData<MutableList<Transaction>>

    @Query("SELECT * FROM transaction_table where status ='F' AND user_id=:userid")
    fun orderInProgress(userid:Int): LiveData<MutableList<Transaction>>

    @Query("SELECT * FROM transaction_table where status !='F'")
    fun allapproved(): LiveData<MutableList<Transaction>>


}