package com.kashifpeer.ksams

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class ClasswiseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classwise)

        setupActionBarWithNavController(findNavController(R.id.fragment3))

    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment3)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}