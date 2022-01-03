package com.example.bsrdigicoin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.navigation.fragment.NavHostFragment

import androidx.navigation.ui.NavigationUI
import com.example.bsrdigicoin.R

import com.google.android.material.bottomnavigation.BottomNavigationView

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_viewer_user) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_user)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)



    }
}