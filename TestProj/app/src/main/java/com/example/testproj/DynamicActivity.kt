package com.example.testproj

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testproj.databinding.ActivityDinamicBinding
import kotlin.random.Random



class DynamicActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDinamicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val container = binding.container


        binding.addButton.setOnClickListener {
            val textToAdd = binding.textInput.text.toString()
            val textViewToAdd = TextView(this).apply {
            text = textToAdd
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                gravity = when (Random.nextInt(3)) {
                    0 -> Gravity.CENTER
                    1 -> Gravity.END
                    else -> Gravity.START
                }
            }
        }
            container.addView(textViewToAdd)
            /*val viewToAdd = layoutInflater.inflate(R.layout.item_text, container, false)
            viewToAdd.apply {
                this.findViewById<TextView>(R.id.textView).text = textToAdd
                findViewById<Button>(R.id.deleteButton).setOnClickListener {
                    container.removeView(this)
                }
            }
            container.addView(viewToAdd)*/
        }


    }
}