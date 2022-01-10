package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {

    var pointEntered = false
    var number1 = "0"; var number2 = ""; var sign = ""
    var result: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        fun getLastItem(): String {
            return changeSymbol(etOperations.text.toString().last().toString())
        }
        fun setResult() {
            if (number1.last() != '.') {
                when (sign) {
                    "+" -> add()
                    "-" -> subtract()
                    "x" -> multiply()
                    "/" -> divide()
                }
                if (!hasSymbols(changeSymbol(etOperations.text.toString()))) etResult.text = "" else etResult.text = result.toString()
            }
        }

        fun printItem(x: String) {
            var input: String = changeSymbol(x) // This is needed because it can be changed, x is a constant
            val operations: String = etOperations.text.toString()
            val lastItem = changeSymbol(etOperations.text.toString().last().toString())
            when {
                operations == "0" && input.isDigitsOnly() -> etOperations.text = "" // Avoids multiple 0
                number1 == "0" && !hasSymbols(lastItem) -> if (input != "." && !hasSymbols(input)) input = "" // Avoids multiple 0
                input == "." && pointEntered -> input = "" // Avoids multiple points
                input == "." && !pointEntered -> pointEntered = true // Allows to enter point again
            }
            when {
                hasSymbols(input) -> {
                    if (lastItem != ".") {
                        Log.d("hello","ENTER")
                        number2 = when (result) {
                            0.0 -> number1
                            else -> result.toString()
                        }
                        number1 = "0"
                        sign = input
                        if (lastItem == ".") input = "" else pointEntered = false
                        if (hasSymbols(lastItem)) input = ""
                    } else { input = "" }
                }
            }
            if (isNumberOrPoint(input)) if (number1 == "0") {
                when(input) {
                    "." -> number1 += input
                    else -> number1 = input
                }
            } else {
                number1 += when (result) {
                    0.0 -> when {
                        hasSymbols(input) || input == "." -> input
                        else -> delPointZero((result + input.toDouble()).toString())
                    }
                    else -> input
                }
            }
            setResult()
            debug()
            input = when (input){
                "x" -> "×"
                "/" -> "÷"
                else -> input
            }
            etOperations.text = etOperations.text.toString() + input
        }
        fun checkPoint(operations: String) { // Allows to enter a point if the number before the sign deleted hasn't one
            var number = ""
            if (hasSymbols(operations)) {
                for (item in operations.reversed()) {
                    if (isNumberOrPoint(item.toString())) number += item.toString() else break
                }
                pointEntered = isDouble(number.reversed())
            } else if (!hasSymbols(operations)) {
                pointEntered = isDouble(operations)
            }
        }
        // Numbers buttons
        btn0.setOnClickListener { printItem(btn0.text.toString()) }
        btn1.setOnClickListener { printItem(btn1.text.toString()) }
        btn2.setOnClickListener { printItem(btn2.text.toString()) }
        btn3.setOnClickListener { printItem(btn3.text.toString()) }
        btn4.setOnClickListener { printItem(btn4.text.toString()) }
        btn5.setOnClickListener { printItem(btn5.text.toString()) }
        btn6.setOnClickListener { printItem(btn6.text.toString()) }
        btn7.setOnClickListener { printItem(btn7.text.toString()) }
        btn8.setOnClickListener { printItem(btn8.text.toString()) }
        btn9.setOnClickListener { printItem(btn9.text.toString()) }
        // Action buttons
        btnPoint.setOnClickListener { printItem(btnPoint.text.toString()) }

        btnDel.setOnClickListener {
            Log.d("hello","item deleted: ${getLastItem()}")
            when (getLastItem()) { // Compares item deleted
                "+","-","x","/" -> {
                    checkPoint(deleteLastItem(etOperations.text.toString()))
                    sign = ""
                }
                else -> {
                    number1 = when(number1.length) {
                        1 -> "0"
                        else -> deleteLastItem(number1)
                    }
                    if (getLastItem() == ".") pointEntered = false
                    setResult()
                }
            }
            etOperations.text = deleteLastItem(etOperations.text.toString())
            if (etOperations.text.isEmpty()) {
                etOperations.text = "0"
                result = 0.0
            }
            debug()
        }
        btnDel.setOnLongClickListener { // Deletes all items
            number1 = ""
            number2 = ""
            result = 0.0
            sign = ""
            etOperations.text = "0"
            etResult.text = ""
            pointEntered = false
            true
        }
        btnPlus.setOnClickListener { printItem(btnPlus.text.toString()) }
        btnMinus.setOnClickListener { printItem(btnMinus.text.toString()) }
        btnMulti.setOnClickListener { printItem("x") }
        btnDivide.setOnClickListener { printItem("/") }
    }
    private fun hasSymbols(input: String): Boolean {
        var symbol = false
        for (item in input) {
            if (item == '+' || item == '-' || item == 'x' || item == '/' || item == '×' || item == '÷') {
                symbol = true
                break
            }
        }
        return symbol
    }
    private fun changeSymbol(symbol: String): String {
        return when (symbol) {
            "×" -> "x"
            "÷" -> "/"
            else -> symbol
        }
    }
    private fun isDouble(expression: String): Boolean {
        var number = expression
        if (number.isEmpty()) {
            return false
        } else {
            if (number.last() == '.') number += "0"
            return number.toDouble() % 1 != 0.0
        }
    }
    private fun isNumberOrPoint(item: String): Boolean {
        return when (item) {
            "0","1","2","3","4","5","6","7","8","9","." -> true
            else -> false
        }
    }
    private fun isNumber(item: String): Boolean {
        return when (item) {
            "0","1","2","3","4","5","6","7","8","9" -> true
            else -> false
        }
    }
    private fun customRound(number: Double, decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return Math.round(number * multiplier) / multiplier
    }
    private fun add() { result = customRound(number2.toDouble() + number1.toDouble(), 3) }
    private fun subtract() { result = customRound(number2.toDouble() - number1.toDouble(), 3) }
    private fun multiply() { result = customRound(number2.toDouble() * number1.toDouble(), 3) }
    private fun divide() {
        result = when(number1) {
            "0" -> result
            else -> customRound(number2.toDouble() / number1.toDouble(), 3)
        }
    }
    private fun delPointZero(number: String): String = number.substring(0 until number.length - 2).also { return it }
    private fun deleteLastItem(input: String): String = input.substring(0 until input.length - 1).also { return it }

    private fun debug() {
        Log.d("hello","number1: $number1 number2: $number2 result: $result point: $pointEntered sign: $sign")
    }
}
