package com.example.mycalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // Clear input
        binding.btnAc.setOnClickListener { binding.input.text = ""
        binding.output.text="" }

        // Number buttons
        binding.btn0.setOnClickListener { binding.input.append("0") }
        binding.btn1.setOnClickListener { binding.input.append("1") }
        binding.btn2.setOnClickListener { binding.input.append("2") }
        binding.btn3.setOnClickListener { binding.input.append("3") }
        binding.btn4.setOnClickListener { binding.input.append("4") }
        binding.btn5.setOnClickListener { binding.input.append("5") }
        binding.btn6.setOnClickListener { binding.input.append("6") }
        binding.btn7.setOnClickListener { binding.input.append("7") }
        binding.btn8.setOnClickListener { binding.input.append("8") }
        binding.btn9.setOnClickListener { binding.input.append("9") }

        // Backspace
        binding.btnBack.setOnClickListener {
            val text = binding.input.text.toString()
            if (text.isNotEmpty()) {
                binding.input.text = text.dropLast(1)
            }
        }

        // Operators
        binding.btnDot.setOnClickListener { binding.input.append(".") }
        binding.btnPlus.setOnClickListener { binding.input.append("+") }
        binding.btnMultiplication.setOnClickListener { binding.input.append("*") } // Fixed: Use "*" instead of "X"
        binding.btnMinus.setOnClickListener { binding.input.append("-") }
        binding.btnDivide.setOnClickListener { binding.input.append("/") }

        // Brackets
        binding.btnStartBracket.setOnClickListener { binding.input.append("(") }
        binding.btnEndBracket.setOnClickListener { binding.input.append(")") }

        // Equals button
        binding.btnEqual.setOnClickListener {
            evaluateExpression()
        }
    }

    private fun evaluateExpression() {
        val expressionText = binding.input.text.toString()

        if (expressionText.isEmpty()) {
            binding.output.text = "Error"
            return
        }

        try {
            val exp = ExpressionBuilder(expressionText).build()
            val result = exp.evaluate()
            val longResult = result.toLong()

            // Display result as integer if no decimal places exist
            binding.output.text = if (result == longResult.toDouble()) {
                longResult.toString()
            } else {
                result.toString()
            }
        } catch (e: Exception) {
            binding.output.text = "Error"
        }
    }
}
