package com.example.bmi_app


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


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

        resultBMI.setOnClickListener {
            ahh()
        }

    }

//    private fun calculateBMI() {
//        val heightStr = inputH.text.toString()
//        val weightStr = inputW.text.toString()
//
//        if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
//            val height = heightStr.toDouble() / 100
//            val weight = weightStr.toDouble()
//
//            val bmi = weight / (height * height)
//
//            val formattedBMI = String.format("%.2f", bmi)
//
//            val resultText = getString(R.string.bmi_result, formattedBMI)
//            resultBMI.text = resultText
//        } else {
//            resultBMI.text = getString(R.string.input_error)
//        }
//    }

    private fun calculateBMI() {
        val heightStr = inputH.text.toString()
        val weightStr = inputW.text.toString()

        if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
            val height = heightStr.toDouble() / 100
            val weight = weightStr.toDouble()

            val bmi = weight / (height * height)

            val formattedBMI = String.format("%.2f", bmi)



            val resultText = when {
                bmi < 16.0 -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                    "Underweight"
                }
                bmi in 16.0..16.99 -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    "Severe Thinness"
                }
                bmi in 17.0..18.49 -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                    "Mild Thinness"
                }
                bmi in 18.5..24.99 -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.green))
                    "Normal Weight"
                }
                bmi in 25.0..29.9 -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    "Overweight"
                }
                bmi in (30.0..34.99) -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                    "Obesity Class I"
                }
                bmi in (35.0..39.99) -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                    "Obesity Class II"
                }
                else -> {
                    resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                    "Obesity Class III (Morbidly Obese)"
                }
            }

            resultBMI.text = getString(R.string.bmi_result, formattedBMI)
            resultBMI.append("\n$resultText")
        } else {
            resultBMI.text = getString(R.string.input_error)
        }
    }


    private fun ahh(){
        val result = Intent(this,
            ResultActivity::class.java)

        startActivity(result)
    }

}
