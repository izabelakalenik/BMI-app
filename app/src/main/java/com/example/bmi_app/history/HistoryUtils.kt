package com.example.bmi_app.history

import android.content.Context
import com.example.bmi_app.ResultBMI
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun currentDateAsString(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}


fun saveBMIResult(context: Context, bmiResult: ResultBMI) {
    val CONSTANT_KEY = "BMI_HISTORY_LIST"
    val MAX_HISTORY_SIZE = 10

    val sharedPreferences = context.getSharedPreferences("BMI_HISTORY", Context.MODE_PRIVATE)
    val gson = Gson()
    val bmiResults = loadBMIResults(context).toMutableList()

    bmiResults.add(0, bmiResult)

    if (bmiResults.size > MAX_HISTORY_SIZE) {
        bmiResults.subList(MAX_HISTORY_SIZE, bmiResults.size).clear()
    }

    val json = gson.toJson(bmiResults)
    sharedPreferences.edit().putString(CONSTANT_KEY, json).apply()
}


fun loadBMIResults(context: Context): List<ResultBMI> {
    val CONSTANT_KEY = "BMI_HISTORY_LIST"

    val sharedPreferences = context.getSharedPreferences("BMI_HISTORY", Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(CONSTANT_KEY, null)
    val gson = Gson()
    val type = object : TypeToken<List<ResultBMI>>() {}.type

    return gson.fromJson(json, type) ?: emptyList()
}
