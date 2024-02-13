package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operation: Char = ' '
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val button0: Button = findViewById(R.id.btn0)
        val button1: Button = findViewById(R.id.btn1)
        val button2: Button = findViewById(R.id.btn2)
        val button3: Button = findViewById(R.id.btn3)
        val button4: Button = findViewById(R.id.btn4)
        val button5: Button = findViewById(R.id.btn5)
        val button6: Button = findViewById(R.id.btn6)
        val button7: Button = findViewById(R.id.btn7)
        val button8: Button = findViewById(R.id.btn8)
        val button9: Button = findViewById(R.id.btn9)
        val buttonDot: Button = findViewById(R.id.btnDecimal)

        val buttonEquals: Button = findViewById(R.id.btnEquals)
        val buttonAdd: Button = findViewById(R.id.btnAdd)
        val buttonSubtract: Button = findViewById(R.id.btnSubtract)
        val buttonMultiply: Button = findViewById(R.id.btnMultiply)
        val buttonDivide: Button = findViewById(R.id.btnDivide)

        val buttonClear: Button = findViewById(R.id.btnClear)

        val numberClickListener = { number: Int ->
            if (isNewOperation) {
                resultTextView.text = ""
                operand2 = 0.0
                isNewOperation = false
            }
            resultTextView.append(number.toString())
        }

        button0.setOnClickListener { numberClickListener(0) }
        button1.setOnClickListener { numberClickListener(1) }
        button2.setOnClickListener { numberClickListener(2) }
        button3.setOnClickListener { numberClickListener(3) }
        button4.setOnClickListener { numberClickListener(4) }
        button5.setOnClickListener { numberClickListener(5) }
        button6.setOnClickListener { numberClickListener(6) }
        button7.setOnClickListener { numberClickListener(7) }
        button8.setOnClickListener { numberClickListener(8) }
        button9.setOnClickListener { numberClickListener(9) }

        buttonDot.setOnClickListener {
            if (isNewOperation) {
                resultTextView.text = "0."
                isNewOperation = false
            } else if (!resultTextView.text.contains(".")) {
                resultTextView.append(".")
            }
        }

        buttonAdd.setOnClickListener {
            handleOperation('+')
        }
        buttonSubtract.setOnClickListener {
            handleOperation('-')
        }
        buttonMultiply.setOnClickListener {
            handleOperation('*')
        }
        buttonDivide.setOnClickListener {
            handleOperation('/')
        }

        buttonEquals.setOnClickListener {
            if (!isNewOperation) {
                operand2 = resultTextView.text.toString().toDouble()
                calculateResult()
                isNewOperation = true

            }
        }

        buttonClear.setOnClickListener {
            resultTextView.text = "0"
            operand1 = 0.0
            operand2 = 0.0
            operation = ' '
            isNewOperation = true
        }
    }

    private fun handleOperation(op: Char) {
        operation = op
        if (!isNewOperation) {
            operand2 = resultTextView.text.toString().toDouble()
            calculateResult()
            isNewOperation = true
        }

    }

    private fun calculateResult() {
        when (operation) {
            '+' -> operand1 += operand2
            '-' -> operand1 -= operand2
            '*' -> operand1 *= operand2
            '/' -> operand1 /= operand2
        }
        resultTextView.text = operand1.toString()
    }
}
