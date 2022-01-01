package com.example.bsrdigicoin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.adapter.AdminTransactionDashboardAdapter
import com.example.bsrdigicoin.adapter.TransactionDashboardAdapter
import com.example.bsrdigicoin.databinding.FragmentAdminTransactionBinding
import com.example.bsrdigicoin.databinding.FragmentUserTransactionBinding
import com.example.bsrdigicoin.db.TransactionDatabase

class AdminTransactionFragment : Fragment() {
    private lateinit var binding: FragmentAdminTransactionBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: AdminTransactionDashboardAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_transaction,container,false)

        val db = Room.databaseBuilder(requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
        val transactionDao = db.transactionDao()

        val transactionList = transactionDao.getAll()

        layoutManager = LinearLayoutManager(activity)

        dashboardAdapter = AdminTransactionDashboardAdapter(
            requireContext(),
            transactionList,
        )

        binding.recyclerViewAdminTransaction.adapter = dashboardAdapter
        binding.recyclerViewAdminTransaction.layoutManager = layoutManager

        return binding.root
    }
}