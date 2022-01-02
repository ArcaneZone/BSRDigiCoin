package com.example.bsrdigicoin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.adapter.AdminReviewTransactionAdapter
import com.example.bsrdigicoin.databinding.FragmentReviewTransactionBinding
import com.example.bsrdigicoin.db.TransactionDatabase
import com.example.bsrdigicoin.viewmodel.AdminTransactionViewModel
import com.example.bsrdigicoin.viewmodel.AdminTransactionViewModelFactory

class ReviewTransactionFragment : Fragment() {

    private lateinit var binding: FragmentReviewTransactionBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var model: AdminTransactionViewModel

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
        val modelfactory=AdminTransactionViewModelFactory(requireContext());

        model = ViewModelProvider(this,modelfactory)[AdminTransactionViewModel::class.java]

        layoutManager = LinearLayoutManager(activity)
        model.TransactionForReview.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewUserTransaction.adapter  = AdminReviewTransactionAdapter(
            requireContext(),
            it,
        )
        })

        binding.recyclerViewUserTransaction.layoutManager = layoutManager

        return binding.root
    }
}