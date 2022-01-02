package com.example.bsrdigicoin.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.adapter.AdminReviewTransactionAdapter
import com.example.bsrdigicoin.adapter.TransactionDashboardAdapter
import com.example.bsrdigicoin.databinding.FragmentUserTransactionBinding
import com.example.bsrdigicoin.db.TransactionDatabase
import com.example.bsrdigicoin.model.UserTransaction
import com.example.bsrdigicoin.viewmodel.AdminTransactionViewModel
import com.example.bsrdigicoin.viewmodel.AdminTransactionViewModelFactory


class UserTransactionFragment : Fragment() {

    private lateinit var binding: FragmentUserTransactionBinding

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: TransactionDashboardAdapter
    private lateinit var model: AdminTransactionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_user_transaction, container, false)
        val modelfactory= AdminTransactionViewModelFactory(requireContext());

        model = ViewModelProvider(this,modelfactory)[AdminTransactionViewModel::class.java]

        layoutManager = LinearLayoutManager(activity)
        model.allTransaction.observe(viewLifecycleOwner, Observer {
            dashboardAdapter = TransactionDashboardAdapter(
                requireContext(),
                it,
            )
        })

        binding.recyclerViewUserTransaction.adapter = dashboardAdapter
        binding.recyclerViewUserTransaction.layoutManager = layoutManager

        return binding.root
    }

}