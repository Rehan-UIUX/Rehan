package com.dicoding.kotlin2

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class kalkulator : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var lastOperator = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
            R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
            R.id.btn_clear, R.id.btn_equals
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                playButtonSound()
                handleButtonClick(it as Button)
            }
        }

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            playButtonSound()
            finish()
        }
    }

    private fun playButtonSound() {
        val mediaPlayer = MediaPlayer.create(this, R.raw.button_click)
        mediaPlayer.setOnCompletionListener { it.release() }
        mediaPlayer.start()
    }

    private fun handleButtonClick(button: Button) {
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> clear()
            "=" -> calculateResult()
            "+", "-", "x", "/" -> handleOperator(buttonText)
            else -> handleNumber(buttonText)
        }
    }

    private fun clear() {
        currentInput = ""
        lastOperator = ""
        result = 0.0
        display.text = ""
    }

    private fun handleNumber(number: String) {
        currentInput += number
        display.text = currentInput
    }

    private fun handleOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            if (result == 0.0) {
                result = currentInput.toDouble()
            } else {
                calculateIntermediateResult()
            }
        }
        lastOperator = operator
        currentInput = ""
    }

    private fun calculateIntermediateResult() {
        when (lastOperator) {
            "+" -> result += currentInput.toDouble()
            "-" -> result -= currentInput.toDouble()
            "x" -> result *= currentInput.toDouble()
            "/" -> result /= currentInput.toDouble()
        }
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && lastOperator.isNotEmpty()) {
            calculateIntermediateResult()
            display.text = result.toString()
            currentInput = ""
            lastOperator = ""
            result = 0.0
        }
    }
}
