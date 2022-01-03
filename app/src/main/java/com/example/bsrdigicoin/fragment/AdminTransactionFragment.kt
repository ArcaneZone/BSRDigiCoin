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
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.adapter.AdminTransactionDashboardAdapter
import com.example.bsrdigicoin.databinding.FragmentAdminTransactionBinding
import com.example.bsrdigicoin.viewmodel.AdminTransactionViewModel
import com.example.bsrdigicoin.viewmodel.AdminTransactionViewModelFactory

class AdminTransactionFragment : Fragment() {
    private lateinit var binding: FragmentAdminTransactionBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var model: AdminTransactionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_admin_transaction,container,false)

        val modelfactory= AdminTransactionViewModelFactory(requireContext())

        model = ViewModelProvider(this,modelfactory)[AdminTransactionViewModel::class.java]

        layoutManager = LinearLayoutManager(activity)

        model.adminApprovedTransaction.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewAdminTransaction.adapter  = AdminTransactionDashboardAdapter(
                requireContext(),
                it,
            )
        })

        binding.recyclerViewAdminTransaction.layoutManager = layoutManager

        return binding.root
    }
}