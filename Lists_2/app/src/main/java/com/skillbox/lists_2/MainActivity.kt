package com.skillbox.lists_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::bind)
    private var pressedTime: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     /*   if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(binding.containerActivity.id, HostFragment(), HostFragment.HOST_FRAGMENT_TAG)
                .addToBackStack(null)
                .commit()
        }*/
        //For Test Transaction
        supportFragmentManager.beginTransaction()
            .replace(binding.containerActivity.id, HostFragment(), HostFragment.HOST_FRAGMENT_TAG)
            .commit()

    }

    //For Test Transaction
    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount
        if (backStackCount == 0) {
            super.onBackPressed()
        } else
            supportFragmentManager.popBackStack()
    }
    /*override fun onBackPressed() {
        val backStackCount =
            supportFragmentManager.findFragmentByTag(HostFragment.HOST_FRAGMENT_TAG)?.childFragmentManager?.backStackEntryCount
        if (backStackCount == 0) {
            if (pressedTime + 2000 > System.currentTimeMillis()) {
                finish()
            } else {
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            }
            pressedTime = System.currentTimeMillis()
        } else {
            supportFragmentManager.findFragmentByTag(HostFragment.HOST_FRAGMENT_TAG)?.childFragmentManager?.popBackStack()
        }
    }*/

}

