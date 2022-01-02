package com.example.bsrdigicoin.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase

class AdminTransactionViewModel(context: Context): ViewModel() {
    val db = Room.databaseBuilder(context, TransactionDatabase::class.java, "transaction_database").allowMainThreadQueries().build()
    val transactionDao = db.transactionDao()
    internal val allTransaction : LiveData<List<Transaction>> = db.transactionDao().getAll()
    internal val TransactionForReview : LiveData<MutableList<Transaction>> = db.transactionDao().getAllForReview()
}