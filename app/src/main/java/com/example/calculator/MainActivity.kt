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

        var pointEntered = false
        var number1: Double
        var number2: Double

        val etOperations = findViewById<TextView>(R.id.etOperations)
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
            val lastItem = etOperations.text.toString().last()
            when {
                operations == "0" && input.isDigitsOnly() -> etOperations.text = "" // Avoids multiple 0
                input == "." && pointEntered -> input = "" // Avoids multiple points
                input == "." && !pointEntered -> pointEntered = true // Allows to enter point again
                input == "+" || input == "-" || input == "×" || input == "÷" -> if (lastItem == '.') input = "" else pointEntered = false
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
        fun isDouble(expression: String): Boolean {
            var number = expression
            if (number.isEmpty()) {
                return false
            } else {
                if (number.last() == '.') number += "0"
                return number.toDouble() % 1 != 0.0
            }
        }
        fun checkPoint(operations: String) { // Allows to enter a point if the number before the sign deleted hasn't one
            var number: String = ""
            if (hasSymbols(operations)) {
                for (item in operations.reversed()) {
                    when (item) {
                        '0','1','2','3','4','5','6','7','8','9','.' -> number += item.toString()
                        '+','-','×','÷' -> break
                    }
                }
                pointEntered = isDouble(number.reversed())
            } else if (!hasSymbols(operations)) {
                pointEntered = isDouble(operations)
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
                '.' -> pointEntered = false
                '+', '-', '×', '÷' -> checkPoint(etOperations.text.substring(0 until etOperations.text.length - 1))
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
            pointEntered = false
            true
        }
        btnPlus.setOnClickListener { printButton(btnPlus.text.toString()) }
        btnMinus.setOnClickListener { printButton(btnMinus.text.toString()) }
        btnMulti.setOnClickListener { printButton(btnMulti.text.toString()) }
        btnDivide.setOnClickListener { printButton(btnDivide.text.toString()) }
    }
}
