package com.example.bsrdigicoin.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.activity.AdminActivity
import com.example.bsrdigicoin.adapter.AdminTransactionDashboardAdapter
import com.example.bsrdigicoin.adapter.RecentTransactionAdapter
import com.example.bsrdigicoin.adapter.TransactionDashboardAdapter
import com.example.bsrdigicoin.databinding.FragmentAdminHomeBinding
import com.example.bsrdigicoin.db.TransactionDatabase

class AdminHomeFragment : Fragment() {
    private lateinit var binding: FragmentAdminHomeBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dashboardAdapter: RecentTransactionAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_home,container,false)

        //(activity as AdminActivity).supportActionBar?.title = "Admin Home"


        val db = Room.databaseBuilder(requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()

        val transactionDao = db.transactionDao()
        val transactionList = transactionDao.getLastest3()

        layoutManager = LinearLayoutManager(activity)

        dashboardAdapter = RecentTransactionAdapter(
            requireContext(),
            transactionList,
        )

        binding.recentTransactionRecycler.adapter = dashboardAdapter
        binding.recentTransactionRecycler.layoutManager = layoutManager


        binding.btnReviewTransaction.text = transactionDao.getReviewToCheck().toString()

        if(transactionDao.getReviewToCheck()==0) {
            binding.btnReviewTransaction.setBackgroundColor(Color.GREEN)
        } else {
            binding.btnReviewTransaction.setBackgroundColor(Color.RED)
        }

        binding.btnReviewTransaction.setOnClickListener{
            findNavController().navigate(R.id.action_adminHomeFragment_to_reviewTransactionFragment)
        }

        return binding.root
    }

}