package com.example.basiccoroutineexample

    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.EditText
    import android.widget.TextView
    import com.example.basiccoroutine.databinding.ActivityMainBinding
    import kotlinx.coroutines.*
    import kotlinx.coroutines.Dispatchers.Main

    class MainActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding
        private lateinit var editText: EditText
        private lateinit var textView: TextView
        private var firstTime = false
        private var currentValue = 0


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)


            editText = binding.numberAddedET
            textView = binding.resultET
            binding.addButton.setOnClickListener {
                val inputValue = editText.text.toString().toIntOrNull()
                if (inputValue != null) {
                    currentValue = inputValue
                    increaseNumber()
                }else if (!firstTime){
                    addOneAnotherTime()
                }


            }
        }

        private fun increaseNumber() {

            CoroutineScope(Main).launch {
                textView.text = currentValue.toString()
                binding.addButton.isEnabled = false
                editText.text.clear()
                delay(2000) // Delay for 2 seconds
                currentValue++
                textView.text = currentValue.toString()
                binding.addButton.isEnabled = true

            }
        }

        private fun addOneAnotherTime(){
            CoroutineScope(Main).launch {
                binding.addButton.isEnabled = false // Disable the button temporarily
                delay(2000)
                currentValue++
                textView.text = currentValue.toString()
                binding.addButton.isEnabled = true
            }
        }


    }
