package com.example.bsrdigicoin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Query("SELECT * FROM user_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE userId=:userid")
    fun getUserById(userid:Int): LiveData<User>
    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username=:username and userPassword=:password)")
    fun ifUserExist(username:String,password:String) : Boolean
    @Query("SELECT * FROM user_table WHERE username=:username and userPassword=:password")
    fun getUserByUserName(username:String,password:String): User?
    @Query("Update user_table set userTotalCount=userTotalCount + :stockCount where userId=:userid")
    fun updateStockCount(stockCount:Int,userid:Int)
    @Query("Update user_table set userBalance=userBalance + :transactionAmount where userId=:userid")
    fun updateTotalValue(transactionAmount:Int,userid:Int)
    @Query("SELECT userTotalCount FROM user_table where userId=:userid")
    fun getTotalStocks(userid: Int): Int
}