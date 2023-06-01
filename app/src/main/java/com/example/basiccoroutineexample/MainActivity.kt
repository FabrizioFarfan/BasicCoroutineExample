package com.example.basiccoroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.basiccoroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var firstTime = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {



            if (!firstTime){
                lifecycleScope.launch(Main) {
                    addOneFirstTime()
                }
            } else {
                lifecycleScope.launch(Main) {
                    addOneMoreTimes()
                }
            }

        }

    }

    private suspend fun addOneFirstTime() {

        val numberEditText = binding.numberAddedET
        val resultTextView = binding.resultET

        var numberToIncrease = numberEditText.text.toString().toIntOrNull()

        resultTextView.text = numberToIncrease.toString()

        if (numberToIncrease != null) {

           val newNumber = CoroutineScope(Main).async {

                delay(2000)
                numberToIncrease++
                firstTime = true
                resultTextView.text = numberToIncrease.toString()
            }
            newNumber.await()

        } else { resultTextView.text = "Insert a Number" }

    }

    private suspend fun addOneMoreTimes(){

        val resultTextView = binding.resultET
        val resultTextViewValue = binding.resultET.text.toString().toInt()

        val newNumber = CoroutineScope(Main).async {

            delay(2000)

            firstTime = true
            resultTextView.text = resultTextViewValue.plus(1).toString()
        }
        newNumber.await()

    }

}
