package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var point = false
        var number1: Double
        var number2: Double
        var x: String

        val etOperations: TextView = findViewById<TextView>(R.id.etOperations)
        val etResult = findViewById<TextView>(R.id.etResult)
        val btnDel = findViewById<Button>(R.id.btnDel)
        val btnDivide = findViewById<Button>(R.id.btnDivide)
        val btnMulti = findViewById<Button>(R.id.btnMulti)
        val btnSubstract = findViewById<Button>(R.id.btnSubstract)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnEquals = findViewById<Button>(R.id.btnEquals)
        val btnPoint = findViewById<Button>(R.id.btnPoint)

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

        fun printButton(x: String) {
            var btn = x
            if (etOperations.text.equals("0") && btn != "." && btn != "+" && btn != "-" && btn != "÷" && btn != "×") { // Avoids multiple 0
                etOperations.text = ""
            }
            if (btn == "." && point) { // Avoids multiple points
                btn = ""
            } else if (btn == "." && !point) {
                point = true
            }
            etOperations.text = etOperations.text.toString() + btn
        }
        // Numbers buttons
        btn0.setOnClickListener { printButton(btn0.text.toString()) }
        btn1.setOnClickListener { printButton(btn1.text.toString()) }
        btn2.setOnClickListener { printButton(btn2.text.toString()) }
        btn3.setOnClickListener { printButton(btn3.text.toString()) }
        btn4.setOnClickListener { printButton(btn4.text.toString()) }
        btn5.setOnClickListener { printButton(btn5.text.toString()) }
        btn6.setOnClickListener { printButton(btn6.text.toString()) }
        btn7.setOnClickListener { printButton(btn7.text.toString()) }
        btn8.setOnClickListener { printButton(btn8.text.toString()) }
        btn9.setOnClickListener { printButton(btn9.text.toString()) }
        // Action buttons
        btnPoint.setOnClickListener { printButton(btnPoint.text.toString()) }

        btnDel.setOnClickListener { // Deletes the lasted item
            val itemDeleted: Char = etOperations.text.toString().last()
            Toast.makeText(this,"item deleted: $itemDeleted",Toast.LENGTH_SHORT).show()
            when (itemDeleted) {
                '.' -> point = false
                '+' -> "hell"
            }
            if (!etOperations.text.equals("0")) {
                etOperations.text = etOperations.text.substring(0 until etOperations.text.length - 1)
            }
            if (etOperations.text.isEmpty()) {
                etOperations.text = "0"
            }
        }
        btnDel.setOnLongClickListener { // Deletes all items
            etOperations.text = "0"
            etResult.text = ""
            point = false
            true
        }
    }
}
