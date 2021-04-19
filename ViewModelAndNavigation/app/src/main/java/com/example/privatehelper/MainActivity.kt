package com.example.privatehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.privatehelper.databinding.ActivityMainBinding
import com.example.privatehelper.extensions.transaction
import com.example.privatehelper.fragments.MainFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding (ActivityMainBinding::bind)
    private var pressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction(
                R.id.containerActivity,
                MainFragment(),
                addToBackStack = false
            )
        }
    }

    override fun onBackPressed() {
        val backStackCount =
            supportFragmentManager.backStackEntryCount
        if (backStackCount == 0) {
            if (pressedTime + 2000 > System.currentTimeMillis()) {
                finish()
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
            pressedTime = System.currentTimeMillis()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}