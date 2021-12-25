package com.example.bsrdigicoin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentLoginBinding
import com.example.bsrdigicoin.viewmodel.LoginViewModel
import androidx.lifecycle.ViewModelProviders








class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        loginViewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]

        binding.lifecycleOwner = this;

        binding.loginViewModel = loginViewModel;

        binding.btnLoginButton.setOnClickListener {
            if(binding.toggleButtonGroup.checkedButtonId== R.id.toggle_btn_admin){
                Toast.makeText(requireContext(),"Admin",Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_loginFragment_to_adminActivity)
            }
            else if(binding.toggleButtonGroup.checkedButtonId== R.id.toggle_btn_user){
                Toast.makeText(requireContext(),"User",Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_loginFragment_to_userActivity)
            }
            else{
                Toast.makeText(requireContext(),"No Option Selected!",Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


}