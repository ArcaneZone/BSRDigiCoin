package com.example.bsrdigicoin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentAdminHomeBinding

class AdminHomeFragment : Fragment() {
    private lateinit var binding: FragmentAdminHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_home,container,false)

        binding.btnReviewTransaction.setOnClickListener{
            findNavController().navigate(R.id.action_adminHomeFragment_to_reviewTransactionFragment)
        }

        return binding.root
    }
}