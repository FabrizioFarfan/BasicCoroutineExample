package com.example.basiccoroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.basiccoroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var job: Job? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
                if(job!=null){
                    job!!.cancel()
                }
                lifecycleScope.launch(Main) {
                    addNumber()

                }

        }

    }

    private suspend fun addNumber() {

        val numberEditText = binding.numberAddedET
        var resultTextView = binding.resultET

        var numberToIncrease = numberEditText.text.toString().toIntOrNull()

        resultTextView.text = numberToIncrease.toString()

        if (numberToIncrease != null) {
           job = CoroutineScope(Main).launch {

                    delay(2000)
                    numberToIncrease++
                    resultTextView.text = numberToIncrease.toString()
          

            }
            job!!.join()
        } else { resultTextView.text = "Insert a Number" }

    }

}
