package com.example.bsrdigicoin.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase
import com.example.bsrdigicoin.db.User
import com.example.bsrdigicoin.model.LoginUser

class UserViewModel(context: Context): ViewModel() {
    val db = Room.databaseBuilder(context, TransactionDatabase::class.java, "transaction_database").allowMainThreadQueries().build()
    val transactionDao = db.transactionDao()
    val userDao= db.userDao()
    val prefs = context?.getSharedPreferences(
        "sharedpreference", Context.MODE_PRIVATE
    )
    val userid= prefs!!.getInt("userid",-1)
    internal val allTransaction : LiveData<List<Transaction>> = db.transactionDao().getAll()
    internal val TransactionForReview : LiveData<MutableList<Transaction>> = db.transactionDao().getAllForReview()
    internal val userTransactionBooking : LiveData<MutableList<Transaction>> = db.transactionDao().orderInProgress(userid)
    internal val adminApprovedTransaction : LiveData<MutableList<Transaction>> = db.transactionDao().allapproved()
    internal val userByUserId : LiveData<User> = db.userDao().getUserById(userid)
}