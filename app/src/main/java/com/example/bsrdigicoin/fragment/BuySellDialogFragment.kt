package com.example.bsrdigicoin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentBuySellDialogBinding
import com.example.bsrdigicoin.databinding.FragmentUserHomeBinding
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BuySellDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentBuySellDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_buy_sell_dialog,container,false)
        val db = Room.databaseBuilder(
            requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
        val transactionDao = db.transactionDao()

        var stockCount:Int = 0
        binding.increase.setOnClickListener {
            stockCount=stockCount+1
            Toast.makeText(requireContext(),"$stockCount",Toast.LENGTH_SHORT).show()
        }
        binding.decrease.setOnClickListener {
            stockCount=stockCount-1
            Toast.makeText(requireContext(),"$stockCount",Toast.LENGTH_SHORT).show()
        }
        transactionDao.insert(
            Transaction(
                0,
                "ArcaneOP",
                "Bitcoin",
                stockCount,
                "21:12:2021",
                "21:21",
                1000.00,
                2000.00,
                121,
                "Buy",
                "F"
            )
        )

        return binding.root
    }
}