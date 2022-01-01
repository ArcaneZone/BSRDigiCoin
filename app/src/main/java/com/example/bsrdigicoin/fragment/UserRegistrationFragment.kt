package com.example.bsrdigicoin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.bsrdigicoin.databinding.FragmentUserRegistrationBinding
import com.example.bsrdigicoin.db.TransactionDatabase
import com.example.bsrdigicoin.db.User


class UserRegistrationFragment : Fragment() {

    lateinit var binding:FragmentUserRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_user_registration, container, false)

        val db = Room.databaseBuilder(requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
         val userDao=db.userDao()

        binding.btnRegisterButton.setOnClickListener {
            val username:String= binding.etRegisterUsername.editText?.text.toString()
            val userFullName=binding.etRegisterFullName.editText?.text.toString()
            val userEmail:String=binding.etRegisterEmail.editText?.text.toString()
            val userPassword:String=binding.etRegisterPassword.editText?.text.toString()
            val confirmPassword:String=binding.etRegisterConfirmPassword.editText?.text.toString()
            if (userPassword!=confirmPassword || userPassword.isEmpty() || confirmPassword.isEmpty()){
                binding.etRegisterConfirmPassword.error = "Passwords don't match or invalid "
            }
            if (userPassword.length<8){
                binding.etRegisterConfirmPassword.error = "Passwords length is less than 8 "
            }
            else if ( binding.etRegisterEmail.editText?.text.toString().isEmpty()){
                binding.etRegisterEmail.error="Email not valid"
            }
            else if(userFullName.isEmpty()){
                binding.etRegisterFullName.error="Name not valid"
            }
            else if(username.isEmpty()){
                binding.etRegisterUsername.error="username not valid"
            }
            else{
                Toast.makeText(requireContext(), "User Registered", Toast.LENGTH_SHORT).show()
                userDao.insert(User(0,username, userFullName,userEmail,userPassword,0.00,0.00,0.00))
            }

        }

        return binding.root
    }


}