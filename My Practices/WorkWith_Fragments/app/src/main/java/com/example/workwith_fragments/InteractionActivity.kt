package com.example.workwith_fragments

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.workwith_fragments.databinding.ActivityInteractionBinding


private lateinit var binding: ActivityInteractionBinding

class InteractionActivity : AppCompatActivity(), ItemSelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInteractionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onItemSelected(text: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, InfoFragment.newInstance(text))
            .commit()
    }
}