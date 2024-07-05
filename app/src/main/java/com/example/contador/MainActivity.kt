package com.example.contador

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textViewCounter: TextView
    private lateinit var handler: Handler
    private var startTime: Long = 0
    private var isRunning: Boolean = false
    private lateinit var buttonStart: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)
        handler = Handler()
        isRunning = false

        buttonStart = findViewById<Button>(R.id.buttonStart)
        buttonStart.setOnClickListener {
            if (isRunning) {
                stopTimer()
            } else {
                startTimer()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
    }
        private fun startTimer() {
            if (!isRunning) {
                startTime = System.currentTimeMillis()
                handler.postDelayed(timerRunnable, 0)
                isRunning = true
                buttonStart.text = "STOP"
            }
        }
        private fun stopTimer(){
            handler.removeCallbacks(timerRunnable)
            isRunning = false
            buttonStart.text = "START"
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