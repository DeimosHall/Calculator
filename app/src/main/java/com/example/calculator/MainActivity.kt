package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etOperations: TextView = findViewById<TextView>(R.id.etOperations)
        val etResult = findViewById<TextView>(R.id.etResult)

        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)

        fun printButton(key: String) {
            etOperations.text = etOperations.text.toString() + key
        }

        btn0.setOnClickListener {
            printButton(btn0.text.toString())
        }
        btn1.setOnClickListener {
            printButton(btn1.text.toString())
        }
        btn2.setOnClickListener {
            printButton(btn2.text.toString())
        }
        btn3.setOnClickListener {
            printButton(btn3.text.toString())
        }
        btn4.setOnClickListener {
            printButton(btn4.text.toString())
        }
        btn5.setOnClickListener {
            printButton(btn5.text.toString())
        }
        btn6.setOnClickListener {
            printButton(btn6.text.toString())
        }
        btn7.setOnClickListener {
            printButton(btn7.text.toString())
        }
        btn8.setOnClickListener {
            printButton(btn8.text.toString())
        }
        btn9.setOnClickListener {
            printButton(btn9.text.toString())
        }
    }
}