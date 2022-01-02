package com.example.bsrdigicoin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentBuySellDialogBinding
import com.example.bsrdigicoin.databinding.FragmentUserHomeBinding
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase
import com.google.android.material.bottomsheet.BottomSheetDialog


class UserHomeFragment : Fragment() {

    lateinit var binding: FragmentUserHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_home, container, false)
        val db = Room.databaseBuilder(
            requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
        val transactionDao = db.transactionDao()

        binding.btnStockSellBuy.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = layoutInflater.inflate(R.layout.fragment_buy_sell_dialog, null)
            val binding2: FragmentBuySellDialogBinding = FragmentBuySellDialogBinding.bind(view)
            dialog.setContentView(view)
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)

            dialog.show()
            transactionDao.insert(
                Transaction(
                    0,
                    "Bitcoin",
                    "2",
                    2 ,
                    "21:12:2021",
                    "21:21",
                    1000.00,
                    2000.00,
                    121,
                    "Buy",
                    "F"
                )
            )

        }


        return binding.root
    }


}