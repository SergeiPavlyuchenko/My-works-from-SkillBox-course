package com.example.workwith_fragments

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workwith_fragments.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showFragmentButton.setOnClickListener {
            showInfoFragment()
        }

        binding.replaceFragmentButton.setOnClickListener {
            replaceInfoFragment()
        }
    }


    private fun showInfoFragment() {

        val alreadyHasFragment =
            supportFragmentManager.findFragmentById(binding.container.id) != null

        if (!alreadyHasFragment) {
            supportFragmentManager.beginTransaction()
                .add(
                    binding.container.id,
                    InfoFragment.newInstance(binding.dataEditText.text.toString())
                )
                .commit()
        } else {
            toast("Fragment is show")
        }
    }

    private fun replaceInfoFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                binding.container.id,
                InfoFragment.newInstance(binding.dataEditText.text.toString())
            )
            .commit()
    }

    private fun toast(message: String) {
        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
    }
}