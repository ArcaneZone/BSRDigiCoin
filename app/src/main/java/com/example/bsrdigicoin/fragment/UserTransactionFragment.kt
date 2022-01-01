package com.example.bsrdigicoin.fragment

import android.content.Context
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
import com.example.bsrdigicoin.adapter.TransactionDashboardAdapter
import com.example.bsrdigicoin.databinding.FragmentUserTransactionBinding
import com.example.bsrdigicoin.db.TransactionDatabase
import com.example.bsrdigicoin.model.UserTransaction


class UserTransactionFragment : Fragment() {

    private lateinit var binding: FragmentUserTransactionBinding

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: TransactionDashboardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_user_transaction, container, false)
        val db = Room.databaseBuilder(requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
        val transactionDao = db.transactionDao()

        val transactionList = transactionDao.getTransactionbyId(1)

        layoutManager = LinearLayoutManager(activity)

        dashboardAdapter = TransactionDashboardAdapter(
            requireContext(),
            transactionList,
        )

        binding.recyclerViewUserTransaction.adapter = dashboardAdapter
        binding.recyclerViewUserTransaction.layoutManager = layoutManager

        return binding.root
    }

}