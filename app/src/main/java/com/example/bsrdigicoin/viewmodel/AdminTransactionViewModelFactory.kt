package com.example.bsrdigicoin.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AdminTransactionViewModelFactory(var context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminTransactionViewModel::class.java)) {
            return AdminTransactionViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}