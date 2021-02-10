package com.example.fragments_1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), ItemSelectListener {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, LoginFragment())
            .commit()
    }


    override fun onItemSelected() {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, MainFragment())
            .commit()
    }


}




