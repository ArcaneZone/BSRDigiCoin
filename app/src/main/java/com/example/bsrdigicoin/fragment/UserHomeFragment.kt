package com.example.bsrdigicoin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentUserHomeBinding


class UserHomeFragment : Fragment() {

    lateinit var binding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_home, container, false)
        val prefs = context?.getSharedPreferences(
            "sharedpreference", Context.MODE_PRIVATE
        )
        val userid= prefs?.getInt("userid",-1)
        println("userid $userid" )



        binding.btnStockSellBuy.setOnClickListener {
            findNavController().navigate(R.id.action_userHomeFragment_to_buySellDialogFragment)
        }


        return binding.root
    }


}

