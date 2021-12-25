package com.example.bsrdigicoin.viewmodel

import android.view.View
import androidx.compose.ui.autofill.AutofillType
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bsrdigicoin.model.LoginUser


class LoginViewModel: ViewModel() {
    public val  txtUserName:MutableLiveData<String> = MutableLiveData()
    public val  txtPassword:MutableLiveData<String> = MutableLiveData()

    private var userMutableLiveData: MutableLiveData<LoginUser> = MutableLiveData()

    fun getUser():MutableLiveData<LoginUser>{
        return userMutableLiveData
    }

    fun onClick(view: View?) {
        val loginUser = LoginUser(txtUserName.value.toString(), txtPassword.value.toString())
        userMutableLiveData.value = loginUser
    }
}