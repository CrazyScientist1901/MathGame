package com.example.mathpuzzle

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.math_game2.MainActivity
import com.example.math_game2.R

class Start : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the button and set the click listener
        val startButton: Button = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            // Redirect to another activity
            val intent = Intent(this, MainActivity::class.java) // Replace with your target activity
            startActivity(intent)
        }
    }
}
