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
            displayFacts()
        }
    }

    private fun createVars(){
        buttonFacts = findViewById(R.id.buttonFacts)
        authorDescription = findViewById(R.id.authorDescription)
    }


    private fun displayFacts(){
        authorDescription.text = getFact(this)
    }

}