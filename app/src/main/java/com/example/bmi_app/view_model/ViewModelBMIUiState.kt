package com.example.bmi_app.view_model

import com.example.bmi_app.R

data class ViewModelBMIUiState(
    val bmi: Double? = null,
    val resultText: String? = null,
    val color: Int = R.color.black,
    val isMetricUnits: Boolean = true,
)