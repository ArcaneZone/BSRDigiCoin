package com.example.bsrdigicoin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.model.UserTransaction


class TransactionDashboardAdapter(val context: Context, private var itemList: List<Transaction>):
    RecyclerView.Adapter<TransactionDashboardAdapter.ItemViewHolder>(){
    var countryFilterList = listOf<Transaction>()
    init {
        countryFilterList = itemList
    }
    //private val db = DatabaseHandler(context)
    class ItemViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val noOfStock:TextView=view.findViewById(R.id.txt_no_of_stock)
        val stockName:TextView=view.findViewById(R.id.txt_stock_name)
        val stockDate: TextView =view.findViewById(R.id.txt_stock_date)
        val stockTime: TextView =view.findViewById(R.id.txt_stock_time)
        val stockType: TextView =view.findViewById(R.id.txt_stock_transaction_type)
        val stockPrice: TextView =view.findViewById(R.id.txt_stock_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_transaction_single, parent, false)

        return ItemViewHolder(view)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val transaction=itemList[position]
        holder.noOfStock.text=transaction.noOfStock.toString()
        holder.stockName.text= transaction.stockName
        holder.stockDate.text=transaction.stockDate
        holder.stockTime.text=transaction.stockTime
        holder.stockType.text=transaction.stockTransactionType
        holder.stockPrice.text="${transaction.stockTotalPrice}(${transaction.stockSinglePrice})"

    }

    fun filterList(filteredList: ArrayList<Transaction>) {
        itemList = filteredList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}