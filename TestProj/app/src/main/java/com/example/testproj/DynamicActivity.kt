package com.example.testproj

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testproj.databinding.ActivityDinamicBinding
import kotlin.random.Random



class DynamicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinamicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinamicBinding.inflate(layoutInflater)
        val view = R.layout.activity_dinamic
        setContentView(view)



        binding.addButton.setOnClickListener {
            val textToAdd = binding.textInput.text.toString()

            //Не работает код ниже, ни закомментированный, ни открытыый.
            //Хотя он полностью повторяет код из урока



            val viewFromItemText = findViewById<LinearLayout>(R.id.itemTextContainer)
            viewFromItemText.apply {
                this.textView
            }


//            val textViewToAdd = TextView(this).apply {
//                text = textToAdd
//                layoutParams = LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//                ).apply {
//                    gravity = when (Random.nextInt(3)) {
//                        0 -> Gravity.CENTER
//                        1 -> Gravity.END
//                        else -> Gravity.START
//                    }
//                }
//            }
            val viewToAdd = layoutInflater.inflate(R.layout.item_text, binding.container, false)
            viewToAdd.apply {

            }
        }


    }
}