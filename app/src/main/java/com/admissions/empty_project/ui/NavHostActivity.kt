package com.admissions.empty_project.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.admissions.empty_project.data.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}