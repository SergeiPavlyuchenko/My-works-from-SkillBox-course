package com.skillbox.multithreading

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.multithreading.databinding.FragmentRaceConditionBinding

class RaceConditionFragment : Fragment(R.layout.fragment_race_condition) {

    private val binding by viewBinding(FragmentRaceConditionBinding::bind)
    private var value = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.notSynchronizedButton.setOnClickListener {
            notSynchronizedMultithreadingIncrement(
                binding.quantityOfThreadsEditText.text.toString().toInt(),
                binding.incrementEditText.text.toString().toInt()
            )
        }

        binding.synchronizedButton.setOnClickListener {
            synchronizedMultithreadingIncrement(
                binding.quantityOfThreadsEditText.text.toString().toInt(),
                binding.incrementEditText.text.toString().toInt()
            )
        }
    }

    private fun synchronizedMultithreadingIncrement(
        threadCount: Int,
        incrementCount: Int,
        expectedValue: Int = value + threadCount * incrementCount
    ) {
        var startTime = 0L
        var finalTime = 0L
        (0 until threadCount).map {
            startTime = System.currentTimeMillis()
            Thread {
                synchronized(this) {
                    for (i in 0 until incrementCount) {
                        value++
                    }
                }
            }.apply {
                start()
            }
        }.map { it.join() }

        finalTime = System.currentTimeMillis() - startTime
        binding.resultTextView.text = resources.getString(
            R.string.result_string,
            expectedValue,
            "\n",
            value,
            "\n",
            finalTime
        )
        value = 0
    }

    private fun notSynchronizedMultithreadingIncrement(
        threadCount: Int,
        incrementCount: Int,
        expectedValue: Int = value + threadCount * incrementCount
    ) {
        var startTime = 0L
        var finalTime = 0L
        (0 until threadCount).map {
            startTime = System.currentTimeMillis()
            Thread {
                for (i in 0 until incrementCount) {
                    value++
                }
            }.apply {
                start()
            }
        }.map {
            finalTime = System.currentTimeMillis() - startTime
            it.join()
        }

        binding.resultTextView.text = resources.getString(
            R.string.result_string,
            expectedValue,
            "\n",
            value,
            "\n",
            finalTime
        )
        value = 0
    }

}