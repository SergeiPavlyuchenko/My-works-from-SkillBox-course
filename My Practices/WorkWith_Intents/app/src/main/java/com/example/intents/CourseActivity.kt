package com.example.intents

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intents.databinding.ActivityCourseBinding


private lateinit var binding: ActivityCourseBinding
class CourseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleIntentData()
    }

//    https://go.skillbox.ru/profession/profession-android-developer-new/
    private fun handleIntentData() {
        intent.data?.lastPathSegment?.let {
            binding.courseNameTextView.text = it
        }
    }
}