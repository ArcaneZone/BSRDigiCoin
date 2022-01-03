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
        val userid= prefs!!.getInt("userid",-1)
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_buy_sell_dialog,container,false)
        val db = Room.databaseBuilder(
            requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()
        val transactionDao = db.transactionDao()

        val user= db.userDao().getUserById(userid)

        var stockCount:Int = 0
        binding.increase.setOnClickListener {
            stockCount += 1
            binding.integerNumber.text=stockCount.toString()
            Toast.makeText(requireContext(),"$stockCount",Toast.LENGTH_SHORT).show()
        }
        binding.decrease.setOnClickListener {
            stockCount -= 1
            binding.integerNumber.text=stockCount.toString()
            Toast.makeText(requireContext(),"$stockCount",Toast.LENGTH_SHORT).show()
        }

        binding.btnConfirmTransaction.setOnClickListener{
            if (binding.toggleButtonGroup.checkedButtonId==R.id.toggle_btn_buy){
                if (user != null) {
                    db.userDao().updateStockCount(stockCount,userid)
                    transactionDao.insert(
                        Transaction(
                            0,
                            user.userName,
                            "Decoin",
                            stockCount, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME),
                            1000.00,
                            2000.00,
                            user.userId,
                            "buy",
                            "F"
                        ) )
                    it.findNavController().navigate(R.id.action_buySellDialogFragment_to_userHomeFragment)
                }
            }
            else if (binding.toggleButtonGroup.checkedButtonId==R.id.toggle_btn_sell){
                if (user != null) {
                    db.userDao().updateStockCount(-stockCount,userid)
                    transactionDao.insert(
                        Transaction(
                            0,
                            user.userName,
                            "Decoin",
                            stockCount, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")),
                            LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME),
                            1000.00,
                            2000.00,
                            user.userId,
                            "sell",
                            "F"
                        ) )
                    it.findNavController().navigate(R.id.action_buySellDialogFragment_to_userHomeFragment)
                }
            }
            else{
                Toast.makeText(requireContext(), "No option Selected", Toast.LENGTH_SHORT).show()
            }


        }


        return binding.root
    }
}