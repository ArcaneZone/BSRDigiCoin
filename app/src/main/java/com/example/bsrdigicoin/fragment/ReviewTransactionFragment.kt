package com.example.bsrdigicoin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.adapter.AdminReviewTransactionAdapter
import com.example.bsrdigicoin.databinding.FragmentReviewTransactionBinding
import com.example.bsrdigicoin.db.TransactionDatabase

class ReviewTransactionFragment : Fragment() {

    private lateinit var binding: FragmentReviewTransactionBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: AdminReviewTransactionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_review_transaction,
            container,
            false
        )

        val db = Room.databaseBuilder(
            requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()

        val transactionDao = db.transactionDao()
        layoutManager = LinearLayoutManager(activity)
        val transactionList = transactionDao.getAll()

        dashboardAdapter = AdminReviewTransactionAdapter(
            requireContext(),
            transactionList,
        )

        binding.recyclerViewUserTransaction.adapter = dashboardAdapter
        binding.recyclerViewUserTransaction.layoutManager = layoutManager

        return binding.root
    }
}