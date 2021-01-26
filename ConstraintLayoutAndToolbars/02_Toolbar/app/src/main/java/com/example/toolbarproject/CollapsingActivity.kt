package com.example.toolbarproject

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.skillbox.toolbarproject.R
import com.skillbox.toolbarproject.databinding.ActivityCollapsingBinding

lateinit var bindings: ActivityCollapsingBinding

class CollapsingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindings = ActivityCollapsingBinding.inflate(layoutInflater)
        setContentView(bindings.root)
        initToolbar()

    }








    private fun initToolbar() {



    }
}
