package com.example.bsrdigicoin.fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.databinding.FragmentBuySellDialogBinding
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class BuySellDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentBuySellDialogBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val prefs = context?.getSharedPreferences(
            "sharedpreference", Context.MODE_PRIVATE
        )
        val userid = prefs!!.getInt("userid", -1)
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_buy_sell_dialog, container, false)
        val db = Room.databaseBuilder(
            requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
        val transactionDao = db.transactionDao()

        //val user= db.userDao().getUserById(userid)
        //var user: User =
        var userName = ""
        db.userDao().getUserById(userid).observe(viewLifecycleOwner, Observer {
            userName = it.userName
        })

        val availableStock = db.userDao().getTotalStocks(userid)
        var stockCount: Int = 0
        binding.increase.setOnClickListener {
            stockCount += 1
            binding.integerNumber.text = stockCount.toString()
            binding.valueOfTotalStocks.text = (stockCount * 1000).toString()
        }
        binding.decrease.setOnClickListener {
            stockCount -= 1
            binding.integerNumber.text = stockCount.toString()
            binding.valueOfTotalStocks.text = (stockCount * 1000).toString()
        }


        binding.btnConfirmTransaction.setOnClickListener {
            val stockValue = stockCount * 1000.00
            if (binding.toggleButtonGroup.checkedButtonId == R.id.toggle_btn_buy) {
                db.userDao().updateStockCount(stockCount, userid)
                db.userDao().updateTotalValue(stockCount, userid)
                transactionDao.insert(
                    Transaction(
                        0,
                        userName,
                        stockCount,
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.now().truncatedTo(ChronoUnit.SECONDS)
                            .format(DateTimeFormatter.ISO_LOCAL_TIME),
                        1000.00,
                        stockValue,
                        userid,
                        "buy",
                        "F"
                    )
                )
                it.findNavController()
                    .navigate(R.id.action_buySellDialogFragment_to_userHomeFragment)
            } else if (binding.toggleButtonGroup.checkedButtonId == R.id.toggle_btn_sell) {
                if ((availableStock - stockCount) > 0) {
                    db.userDao().updateStockCount(-stockCount, userid)
                    transactionDao.insert(
                        Transaction(
                            0,
                            userName,
                            stockCount,
                            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")),
                            LocalTime.now().truncatedTo(ChronoUnit.SECONDS)
                                .format(DateTimeFormatter.ISO_LOCAL_TIME),
                            1000.00,
                            stockValue,
                            userid,
                            "sell",
                            "F"
                        )
                    )
                    it.findNavController()
                        .navigate(R.id.action_buySellDialogFragment_to_userHomeFragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Insufficient number of Stocks\nYou have ${availableStock} to Sell",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(requireContext(), "No option Selected", Toast.LENGTH_SHORT).show()
            }


        }


        return binding.root
    }
}