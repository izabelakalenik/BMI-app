package com.example.bmi_app.units

class Imperial (private val heightInInches: Double, private val weightInPounds: Double): UnitClass() {
    override fun calculateBMI(): Double {
        return (weightInPounds / (heightInInches * heightInInches)) * 703
    }
}