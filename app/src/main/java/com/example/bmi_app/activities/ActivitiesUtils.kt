package com.example.bmi_app.activities

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.bmi_app.R

fun getResultText(context: Context, bmi: Double) : String{
    val resources = context.resources

    val resultText = when {
        bmi < 16.0 -> resources.getString(R.string.severe_thinness_label)
        bmi in (16.0..16.99) -> resources.getString(R.string.mild_thinness_label)
        bmi in (17.0..18.49) -> resources.getString(R.string.underweight_label)
        bmi in (18.5..24.99) -> resources.getString(R.string.normal_weight_label)
        bmi in (25.0..29.99) -> resources.getString(R.string.overweight_label)
        bmi in (30.0..34.99) -> resources.getString(R.string.obesity_I_label)
        bmi in (35.0..39.99) -> resources.getString(R.string.obesity_II_label)
        else -> resources.getString(R.string.obesity_III_label)
    }
    return resultText
}

fun getColor(context: Context, bmi: Double) : Int {

    val color = when {
        bmi < 16.0 -> ContextCompat.getColor(context, R.color.red)
        bmi in (16.0..16.99) ->
            ContextCompat.getColor(context, R.color.orange)
        bmi in (17.0..18.49) ->
            ContextCompat.getColor(context, R.color.yellow)
        bmi in (18.5..24.99) ->
            ContextCompat.getColor(context, R.color.green)
        bmi in (25.0..29.9) ->
            ContextCompat.getColor(context, R.color.orange)
        bmi in (30.0..34.99) ->
            ContextCompat.getColor(context, R.color.red)
        bmi in (35.0..39.99) ->
            ContextCompat.getColor(context, R.color.red)
        else ->
            ContextCompat.getColor(context, R.color.red)
    }
    return color
}

fun getDescription(context: Context, resultText : String) : String{
    val resources = context.resources

    val description  = when (resultText) {
        "Severe Thinness" -> resources.getString(R.string.severe_thinness)
        "Mild Thinness" -> resources.getString(R.string.mild_thinness)
        "Underweight" -> resources.getString(R.string.underweight)
        "Normal Weight" -> resources.getString(R.string.normal_weight)
        "Overweight" ->  resources.getString(R.string.overweight)
        "Obesity Class I" -> resources.getString(R.string.obesity_I)
        "Obesity Class II" -> resources.getString(R.string.obesity_II)
        else -> resources.getString(R.string.obesity_III)
    }
    return description
}

fun getFact(context: Context) : String{
    val resources = context.resources
    val random = kotlin.random.Random

    val fact = when (random.nextInt(1, 8)) {
        1 -> resources.getString(R.string.fact_1)
        2 -> resources.getString(R.string.fact_2)
        3 -> resources.getString(R.string.fact_3)
        4 -> resources.getString(R.string.fact_4)
        5 -> resources.getString(R.string.fact_5)
        6 -> resources.getString(R.string.fact_6)
        else -> resources.getString(R.string.fact_7)
    }
    return fact
}

