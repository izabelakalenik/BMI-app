package com.example.bmi_app


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var inputH: EditText
    private lateinit var inputW: EditText
    private lateinit var resultBMI: TextView
    private lateinit var buttonBMI: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputH = findViewById(R.id.inputH)
        inputW = findViewById(R.id.inputW)
        resultBMI = findViewById(R.id.resultBMI)
        buttonBMI = findViewById(R.id.buttonBMI)

        buttonBMI.setOnClickListener {
            calculateBMI()
        }

        val send = Intent(this,
            ResultActivity::class.java
        )

        startActivity(send)
    }

    private fun calculateBMI() {
        val heightStr = inputH.text.toString()
        val weightStr = inputW.text.toString()

        if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
            val height = heightStr.toDouble() / 100
            val weight = weightStr.toDouble()

            val bmi = weight / (height * height)

            val formattedBMI = String.format("%.2f", bmi)

            val resultText = getString(R.string.bmi_result, formattedBMI)
            resultBMI.text = resultText
        } else {
            resultBMI.text = getString(R.string.input_error)
        }
    }

}
