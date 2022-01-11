package com.example.bsrdigicoin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.adapter.TransactionDashboardAdapter
import com.example.bsrdigicoin.databinding.FragmentUserHomeBinding
import com.example.bsrdigicoin.db.User
import com.example.bsrdigicoin.viewmodel.UserViewModel
import com.example.bsrdigicoin.viewmodel.UserViewModelFactory


class UserHomeFragment : Fragment() {

    lateinit var binding: FragmentUserHomeBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var model: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_home, container, false)
        val modelfactory = UserViewModelFactory(requireContext())

        model = ViewModelProvider(this, modelfactory)[UserViewModel::class.java]

        layoutManager = LinearLayoutManager(activity)
        model.userTransactionBooking.observe(viewLifecycleOwner, Observer {
            binding.recyclerviewUserOrders.adapter = TransactionDashboardAdapter(
                requireContext(),
                it,
            )
        })

        model.userByUserId.observe(viewLifecycleOwner,Observer{
            binding.userFullnameInput.text = "Full Name: ${it.fullName}"
            binding.userUseridInput.text = "User Id: ${it.userId}"
            binding.userStocksaquiredInput.text = "Stocks Acquired: ${it.userTotalCount}"
            binding.userCurrentvalueinvestedInput.text = "Currently Invested: ${it.TotalValue}"
            binding.userCurrentvalueorderedInput.text = "Currently Ordered: ${it.userOrderValue}(${it.userOrder})"
        })

        binding.recyclerviewUserOrders.layoutManager = layoutManager

        binding.btnStockSellBuy.setOnClickListener {
            findNavController().navigate(R.id.action_userHomeFragment_to_buySellDialogFragment)
        }

        return binding.root
    }


}

