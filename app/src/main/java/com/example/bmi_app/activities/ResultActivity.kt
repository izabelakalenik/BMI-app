package com.example.bmi_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.bmi_app.R

class ResultActivity : AppCompatActivity() {

    private lateinit var resultBMI : TextView
    private lateinit var resultInfoBMI : TextView
    private lateinit var descriptionBMI : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        createVars()
        getData()

    }

    private fun createVars(){
        resultBMI = findViewById(R.id.resultBMI2)
        resultInfoBMI = findViewById(R.id.resultInfoBMI)
        descriptionBMI = findViewById(R.id.descriptionBMI)
    }

    private fun getData(){
        // Odbierz dane przesłane z MainActivity
        val intent = intent
        val bmi = intent.getStringExtra("bmi")
        val resultText = intent.getStringExtra("resultText")
        val color = intent.getIntExtra("color", 0)

        // Teraz możesz wykorzystać te wartości, np. do aktualizacji widoku w ResultActivity
        if (bmi != null && resultText != null) {

            resultBMI.text = bmi
            resultBMI.setTextColor(color)
            resultInfoBMI.text = resultText
            resultInfoBMI.setTextColor(color)
        }
    }
}
