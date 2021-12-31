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
    fun getUserById(userid:Int): User?
    @Query("SELECT EXISTS(SELECT * FROM user_table WHERE username=:username and userPassword=:password)")
    fun ifUserExist(username:String,password:String) : Boolean
}