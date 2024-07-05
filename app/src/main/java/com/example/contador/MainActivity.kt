package com.example.contador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout.LengthCounter
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var textViewCounter: TextView
    private lateinit var handler: Handler
    private var startTime: Long = 0
    private var isRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)
        handler = Handler()
        isRunning = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }

        fun startTimer(view: View){
            if(!isRunning){
                startTime = System.currentTimeMillis()
                handler.postDelayed(timerRunnable,0)
                isRunning = true
            }
        }

        private val timerRunnable = object : Runnable {
            override fun run(){
                val millis = System.currentTimeMillis() - startTime
                var seconds = (millis/1000).toInt()
                val minutes = seconds/60
                val hours = minutes/60

                seconds %=60

                val time =String.format("%02d:%02d:%02d", hours, minutes % 60, seconds)
                textViewCounter.text = time

                handler.postDelayed(this,500)
            }
        }
    }