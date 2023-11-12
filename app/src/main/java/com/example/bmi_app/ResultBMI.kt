package com.example.bmi_app

data class ResultBMI(
    val date: String,
    val height: Double,
    val weight: Double,
    val units: String,
    val bmiValue: Double
)