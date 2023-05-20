package com.example.basiccoroutineexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.basiccoroutineexample.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val number = binding.numberAddedET.text.toString().toInt()
            CoroutineScope(Main).launch{
                addNumber(number)
            }
        }

    }


    private suspend fun addNumber(number: Int){
        binding.numberAddedET.text.clear()
        delay(2000L)
        binding.resultET.text = binding.resultET.text.toString().toInt().plus(number).toString()
    }

}