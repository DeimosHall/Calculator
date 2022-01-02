package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

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
        val btnMinus = findViewById<Button>(R.id.btnMinus)
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
            var input: String = x // This is needed because it can be changed, x is a constant
            val operations: String = etOperations.text.toString()
            when {
                operations == "0" && input.isDigitsOnly() -> etOperations.text = "" // Avoids multiple 0
                input == "." && point -> input = "" // Avoids multiple points
                input == "." && !point -> point = true
                input == "+" || input == "-" || input == "×" || input == "÷" -> point = false
            }
            etOperations.text = etOperations.text.toString() + input
        }
        fun hasSymbols(x: String): Boolean {
            var symbol = false
            for (item in x) {
                if (item == '+' || item == '-' || item == '×' || item == '÷') {
                    symbol = true
                    break
                }
            }
            return symbol
        }
        fun checkPoint(operations: String) {
            if (hasSymbols(operations)) {

            }
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
            when (etOperations.text.toString().last()) { // Compares item deleted
                '.' -> point = false
                '+', '-', '×', '÷' -> checkPoint(etOperations.text.toString())
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
        btnPlus.setOnClickListener { printButton(btnPlus.text.toString()) }
        btnMinus.setOnClickListener { printButton(btnMinus.text.toString()) }
        btnMulti.setOnClickListener { printButton(btnMulti.text.toString()) }
        btnDivide.setOnClickListener { printButton(btnDivide.text.toString()) }
    }
}
