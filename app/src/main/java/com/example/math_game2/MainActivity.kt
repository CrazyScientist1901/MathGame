package com.example.math_game2

import android.os.Bundle
import android.telephony.data.UrspRule
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var number1: EditText
    private lateinit var number2: EditText
    private lateinit var number3: EditText
    private lateinit var number4: EditText
    private lateinit var result1: TextView
    private lateinit var result2: TextView
    private lateinit var result3: TextView
    private lateinit var result4: TextView
    private lateinit var operator: TextView
    private lateinit var buttonCalculate: Button
    private lateinit var buttonExit: Button
    private val operators = arrayOf("+", "-", "X", "÷")
    private var isCalculated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_layout)

        number1 = findViewById(R.id.number1)
        number2 = findViewById(R.id.number2)
        number3 = findViewById(R.id.number3)
        number4 = findViewById(R.id.number4)
        result1 = findViewById(R.id.result1)
        result2 = findViewById(R.id.result2)
        result3 = findViewById(R.id.result3)
        result4 = findViewById(R.id.result4)
        operator = findViewById(R.id.operator)
        buttonCalculate = findViewById(R.id.button_generate) // Renamed for clarity
        buttonExit = findViewById(R.id.button_exit)

        buttonCalculate.setOnClickListener {
            if (!isCalculated) {
                // Generate operator and change button to "Calculate"
                generateRandomOperator()
            } else {
                // If the button text is "Reset", clear the fields
                if (buttonCalculate.text == "Reset") {
                    resetFields()
                } else {
                    // Perform calculation and change button to "Reset"
                    performCalculation()
                }
            }
        }

        buttonExit.setOnClickListener {
            finishAffinity() // Close the app
        }
    }

    private fun generateRandomOperator() {
        val random = Random()
        val index = random.nextInt(operators.size)
        val randomOperator = operators[index]
        operator.text = randomOperator
        buttonCalculate.text = "Calculate" // Change button text to "Calculate"
        isCalculated = true // Set flag to indicate that we can calculate now
    }

    private fun performCalculation() {
        val num1 = number1.text.toString().toDoubleOrNull()
        val num2 = number2.text.toString().toDoubleOrNull()
        val num3 = number3.text.toString().toDoubleOrNull()
        val num4 = number4.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null && num3 != null && num4 != null) {
            val op = operator.text.toString()

            // Calculate results based on the specified pairs
            val result1Value: Double = calculate(num1, num2, op)
            val result2Value: Double = calculate(num3, num4, op)
            val result3Value: Double = calculate(num1, num3, op)
            val result4Value: Double = calculate(num2, num4, op)

            // Display results or error messages
            result1.text = if (result1Value.isNaN()) "Not defined/Error" else "$result1Value"
            result2.text = if (result2Value.isNaN()) "Not defined/Error" else "$result2Value"
            result3.text = if (result3Value.isNaN()) "Not defined/Error" else "$result3Value"
            result4.text = if (result4Value.isNaN()) "Not defined/Error" else "$result4Value"

            // Change button text to "Reset" after calculation
            buttonCalculate.text = "Reset"
        } else {
            result1.text = "Please enter valid numbers."
            result2.text = ""
            result3.text = ""
            result4.text = ""
        }
    }

    private fun calculate(numA: Double, numB: Double, operator: String): Double {
        return when (operator) {
            "+" -> numA + numB
            "-" -> numA - numB
            "X" -> numA * numB
            "÷" -> if (numB != 0.0) numA / numB else Double.NaN // Handle division by zero
            else -> 0.0
        }
    }

    private fun resetFields() {
        number1.text.clear()
        number2.text.clear()
        number3.text.clear()
        number4.text.clear()
        result1.text = "Result1"
        result2.text = "Result2"
        result3.text = "Result3"
        result4.text = "Result4"
        operator.text = "Operator"
        buttonCalculate.text = "Generate" // Reset button text to "Generate"
        isCalculated = false // Reset flag
    }
}
