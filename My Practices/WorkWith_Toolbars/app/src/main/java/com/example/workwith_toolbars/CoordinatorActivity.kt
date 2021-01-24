package com.example.workwith_toolbars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.workwith_toolbars.databinding.ActivityCoordinatorBinding


private lateinit var binding: ActivityCoordinatorBinding

class CoordinatorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoordinatorBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }
}