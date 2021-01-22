package com.example.workwith_toolbars

import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.workwith_toolbars.databinding.ActivityToolbarBinding

class ToolbarActivity: AppCompatActivity() {

    private lateinit var binding: ActivityToolbarBinding

    val users = listOf(
        "User1",
        "User2",
        "Unknown"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()

    }

    private fun initToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            toast("Navigation clicked")
        }

        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_1 -> {
                    toast("Action_1 clicked")
                    true
                }
                R.id.action_2 -> {
                    toast("Action_2 clicked")
                    true
                }
                else -> false
            }
        }
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.expandTextView.text = "search expanded"
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.expandTextView.text = "search collapsed"
                return true
            }

        })


        (searchItem.actionView as androidx.appcompat.widget.SearchView).setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                users.filter { it.contains(newText ?: "",true) }
                    .joinToString()
                    .let { binding.searchResultTextView.text = it }
                return true
            }

        })
    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}