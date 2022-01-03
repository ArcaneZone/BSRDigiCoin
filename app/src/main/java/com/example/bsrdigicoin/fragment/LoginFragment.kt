package com.example.bsrdigicoin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentLoginBinding
import com.example.bsrdigicoin.db.TransactionDatabase


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val db = Room.databaseBuilder(requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()

        val transactionDao = db.userDao()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnSignupButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_userRegistrationFragment)
        }

//        loginViewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]
//
//        binding.lifecycleOwner = this;
//
//        binding.loginViewModel = loginViewModel;

        binding.btnLoginButton.setOnClickListener {
            if (binding.toggleButtonGroup.checkedButtonId == R.id.toggle_btn_admin) {
                if (binding.etLoginUsername.editText?.text!!.toString() == "admin" && binding.etLoginPassword.editText?.text!!.toString() == "admin"
                ) {
                    it.findNavController().navigate(R.id.action_loginFragment_to_adminActivity)
                } else {
                    Toast.makeText(requireContext(), "You are not an Admin", Toast.LENGTH_SHORT)
                        .show()
                }

            } else if (binding.toggleButtonGroup.checkedButtonId == R.id.toggle_btn_user) {
                val bool = transactionDao.ifUserExist(
                    binding.etLoginUsername.editText!!.text.toString(),
                    binding.etLoginPassword.editText!!.text.toString()
                )

                val user=transactionDao.getUserByUserName(binding.etLoginUsername.editText!!.text.toString(),binding.etLoginPassword.editText!!.text.toString())

                if (bool){
                    val userid=user?.userId.toString().toInt()
                    val action=LoginFragmentDirections.actionLoginFragmentToUserActivity(userid)
                    it.findNavController().navigate(action)
                    val sharedPreference = context?.getSharedPreferences("sharedpreference",Context.MODE_PRIVATE)
                    val editor = sharedPreference?.edit()
                    if (editor != null) {
                        editor.putInt("userid",userid)
                        editor.apply()
                    }
                }

                else {
                    Toast.makeText(
                        requireContext(),
                        "User not exist.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else{
                Toast.makeText(
                    requireContext(),
                    "No option is selected!!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        return binding.root
    }


}