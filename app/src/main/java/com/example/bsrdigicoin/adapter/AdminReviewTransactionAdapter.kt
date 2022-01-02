package com.example.bsrdigicoin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.db.Transaction
import com.example.bsrdigicoin.db.TransactionDatabase
import com.google.android.material.button.MaterialButton

class AdminReviewTransactionAdapter(val context: Context, private var itemList : MutableList<Transaction>):
    RecyclerView.Adapter<AdminReviewTransactionAdapter.ItemViewHolder>(){
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
        val btnApprove: MaterialButton = view.findViewById(R.id.btn_transaction_isApproved)
        val btnDisapprove: MaterialButton = view.findViewById(R.id.btn_transaction_isNotApproved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.review_transaction_single, parent, false)

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

        holder.btnApprove.setOnClickListener {
            transactionDao.approveStatus(transaction.transactionId)
            itemList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
        holder.btnDisapprove.setOnClickListener {
            transactionDao.disapproveStatus(transaction.transactionId)
            itemList.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)

        }
        if(transaction.status=="A") {
            //holder.btnApprove.setColorFilter(Color.argb(150,200,200,200))
            holder.btnApprove.visibility = View.VISIBLE
            holder.btnDisapprove.visibility = View.INVISIBLE

        }
        if(transaction.status=="D") {
            //holder.btnApprove.setColorFilter(Color.argb(150, 200, 200, 200))
            holder.btnApprove.visibility = View.INVISIBLE
            holder.btnDisapprove.visibility = View.VISIBLE

        }
    }

    fun filterList(filteredList: ArrayList<com.example.bsrdigicoin.db.Transaction>) {
        itemList = filteredList
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return itemList.size
    }
}