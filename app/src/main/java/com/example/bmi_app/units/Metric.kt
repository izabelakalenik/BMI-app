package com.example.bmi_app.units

class Metric(private val heightInCm: Double, private val weightInKg: Double) : UnitClass() {

    override fun calculateBMI(): Double {
        val heightInMeters = heightInCm / 100
        return weightInKg / (heightInMeters * heightInMeters)
    }
}

