package com.example.bsrdigicoin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase

class AdminTransactionDashboardAdapter(val context: Context, private var itemList : List<Transaction>):
    RecyclerView.Adapter<AdminTransactionDashboardAdapter.ItemViewHolder>(){
    var countryFilterList = listOf<com.example.bsrdigicoin.db.Transaction>()
    init {
        countryFilterList = itemList
    }

    val db = Room.databaseBuilder(context,
        TransactionDatabase::class.java, "transaction_database"
    ).allowMainThreadQueries().build()
    val transactionDao = db.transactionDao()

    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val noOfStock: TextView =view.findViewById(R.id.adm_transaction_noOfStocks)
        val userName: TextView =view.findViewById(R.id.adm_transaction_userName)
        val stockName: TextView =view.findViewById(R.id.adm_transaction_nameStock)
        val stockDate: TextView =view.findViewById(R.id.adm_transaction_date)
        val stockTime: TextView =view.findViewById(R.id.adm_transaction_time)
        val stockType: TextView =view.findViewById(R.id.adm_transaction_stockType)
        val stockPrice: TextView =view.findViewById(R.id.adm_transaction_purchasedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.admin_transaction_single, parent, false)

        return ItemViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val transaction=itemList[position]
        holder.noOfStock.text=transaction.noOfStock.toString()
        holder.userName.text=transaction.userName
        holder.stockName.text= transaction.stockName
        holder.stockDate.text=transaction.stockDate
        holder.stockTime.text=transaction.stockTime
        holder.stockType.text=transaction.status.toString()
        holder.stockPrice.text="${transaction.stockTotalPrice}(${transaction.stockSinglePrice})"

    }

    fun filterList(filteredList: ArrayList<com.example.bsrdigicoin.db.Transaction>) {
        itemList = filteredList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}