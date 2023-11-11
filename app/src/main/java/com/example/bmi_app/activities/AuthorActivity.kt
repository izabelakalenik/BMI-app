package com.example.bmi_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.bmi_app.R

class AuthorActivity : AppCompatActivity() {

    private lateinit var buttonFacts: Button
    private lateinit var authorDescription: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_author)

        createVars()

        buttonFacts.setOnClickListener {
            getFacts()
        }
    }

    private fun createVars(){
        buttonFacts = findViewById(R.id.buttonFacts)
        authorDescription = findViewById(R.id.authorDescription)
    }

    private fun getFacts(){
        val random = kotlin.random.Random

        when (random.nextInt(1, 8)) {
            1 -> authorDescription.text = getString(R.string.fact_1)
            2 -> authorDescription.text = getString(R.string.fact_2)
            3 -> authorDescription.text = getString(R.string.fact_3)
            4 -> authorDescription.text = getString(R.string.fact_4)
            5 -> authorDescription.text = getString(R.string.fact_5)
            6 -> authorDescription.text = getString(R.string.fact_6)
            7 -> authorDescription.text = getString(R.string.fact_7)
        }

    }
}