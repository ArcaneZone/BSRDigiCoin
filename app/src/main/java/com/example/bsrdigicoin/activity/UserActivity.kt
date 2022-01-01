package com.example.bsrdigicoin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.bsrdigicoin.R
import com.example.bsrdigicoin.fragment.LoginFragmentArgs
import com.example.bsrdigicoin.fragment.UserHomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {
  val args: LoginFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_viewer_user) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_user)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)



        val userid=args.myArg
        println("userid act $userid")
        val  bundle = Bundle()
        bundle.putInt("userid",userid)
        val userHomeFragment = UserHomeFragment()
        userHomeFragment.arguments=bundle
        navController.setGraph(navController.graph,bundle)
    }
}