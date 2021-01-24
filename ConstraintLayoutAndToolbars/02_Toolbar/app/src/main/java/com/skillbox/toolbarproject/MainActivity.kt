package com.skillbox.toolbarproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.skillbox.toolbarproject.databinding.ActivityMainBinding
import kotlin.random.Random

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        Glide.with(this)
//                .load("https://photoplay.ru/sites/default/files/imce/2019/travel-photo_dk.jpg")
//                .centerCrop()
//                .into()


        initToolbar()
    }



    private fun showSum() {
        binding.textViewForSum.apply {
            isVisible = true
            text = "Sum = ${(5 + 10)}"
        }
        Handler().postDelayed({
            binding.textViewForSum.text = "Good Bye!"
            Handler().postDelayed({
                binding.textViewForSum.visibility = View.GONE
            }, 2000)
        }, 2000)

    }

    private fun changeTitle() {
        binding.toolbar.title = if (binding.toolbar.title == resources.getString(R.string.app_name)) {
            resources.getString(R.string.app_name_optional)
        } else resources.getString(R.string.app_name)
    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "You are clicked navigation button", Toast.LENGTH_SHORT).show()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_changeLabel -> {
                    changeTitle()
                    true
                }
                R.id.action_showToast -> {
                    Toast.makeText(this, "Current title: ${binding.toolbar.title}", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.action_fiveAndTenSum -> {
                    showSum()
                    true
                }
                else -> false
            }
        }

        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                TODO("Not yet implemented")
            }
        })

    }
}