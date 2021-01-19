package com.example.testprod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.testprod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.clearButton.setOnClickListener {
            binding.nameInput.setText("")
            Toast.makeText(this,R.string.clearText,Toast.LENGTH_SHORT).show()
        }
//
//
//        bind.nameInput.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun afterTextChanged(s: Editable?) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                bind.nameText.text = s?.takeIf { it.isNotBlank() }
//                    ?.let { name -> resources.getString(R.string.hello_string, name) }
//                bind.clearButton.isEnabled = s?.let { it.isNotEmpty() } ?: false
//            }
//        })

//        bind.makeLongOperationButton.setOnClickListener {
//            makeLongOperation()
        }

    }

//    private fun makeLongOperation() {
//        bind.longOperationProgress.visibility = View.VISIBLE
//        bind.makeLongOperationButton.isEnabled = false
//        Handler().postDelayed({
//            bind.longOperationProgress.visibility = View.GONE
//            bind.makeLongOperationButton.isEnabled = true
//            Toast.makeText(this, R.string.long_operation_complete, Toast.LENGTH_SHORT).show()
//        }, 2000)
//    }

//}