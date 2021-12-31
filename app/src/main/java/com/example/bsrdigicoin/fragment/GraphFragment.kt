package com.example.bsrdigicoin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.room.Room
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.db.TransactionDatabase


class GraphFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_graph, container, false)
        val db = Room.databaseBuilder(requireContext(),
            TransactionDatabase::class.java, "transaction_database"
        ).allowMainThreadQueries().build()

        val transactionDao = db.userDao()

        val username:TextView=view.findViewById(R.id.temp)
        username.text=transactionDao.getUserById(1)?.fullName
        return  view
    }


}