package com.example.testproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.testproj.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.clearButton.setOnClickListener {
            binding.nameInput.setText("")
            Toast.makeText(this,R.string.clear_text,Toast.LENGTH_SHORT).show()
        }

        binding.nameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               binding.nameText.text = s?.takeIf { it.isNotBlank() }
                   ?.let {name -> getString(R.string.hello_string, name)}
                binding.clearButton.isEnabled = s?.isNotEmpty() ?: false
            }
        })




        binding.makeLongOperationButton.setOnClickListener {
           makeLongOperation()
        }

    }

    private fun makeLongOperation() {
        binding.longOperationProgress.visibility = View.VISIBLE
        binding.makeLongOperationButton.isEnabled = false
        Handler().postDelayed({
            binding.longOperationProgress.visibility = View.GONE
            binding.makeLongOperationButton.isEnabled = true
            Toast.makeText(this, R.string.long_operation_complete, Toast.LENGTH_SHORT).show()
        }, 2000)
    }


}



//binding.clearButton.setOnClickListener {
//    binding.nameInput.setText("")
//    Toast.makeText(this,R.string.clearText,Toast.LENGTH_SHORT).show()
//}
//
//
//binding.nameInput.addTextChangedListener(object : TextWatcher {
//    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//    override fun afterTextChanged(s: Editable?) {}
//
//    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        binding.nameText.text = s?.takeIf { it.isNotBlank() }
//            ?.let { name -> resources.getString(R.string.hello_string, name) }
//        binding.clearButton.isEnabled = s?.let { it.isNotEmpty() } ?: false
//    }
//})
//
//binding.makeLongOperationButton.setOnClickListener {
//    makeLongOperation()
//}
//
//}
//
//private fun makeLongOperation() {
//    binding.longOperationProgress.visibility = View.VISIBLE
//    binding.makeLongOperationButton.isEnabled = false
//    Handler().postDelayed({
//        binding.longOperationProgress.visibility = View.GONE
//        binding.makeLongOperationButton.isEnabled = true
//        Toast.makeText(this, R.string.long_operation_complete, Toast.LENGTH_SHORT).show()
//    }, 2000)
//}