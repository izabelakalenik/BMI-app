package com.example.bmi_app.history
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

const val CONST_NAME = "BMI_HISTORY"
const val CONST_KEY = "BMI_HISTORY_LIST"


fun currentDateAsString(): String {
    val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    val date = Date()
    return dateFormat.format(date)
}

fun saveBMIResult(context: Context, bmiResult: ResultBMI) {
    val maxHistorySize = 10

    val sharedPreferences = context.getSharedPreferences(CONST_NAME, Context.MODE_PRIVATE)
    val gson = Gson()
    val bmiResults = loadBMIResults(context).toMutableList()

    bmiResults.add(0, bmiResult)

    if (bmiResults.size > maxHistorySize) {
        bmiResults.subList(maxHistorySize, bmiResults.size).clear()
    }

    val json = gson.toJson(bmiResults)
    sharedPreferences.edit().putString(CONST_KEY, json).apply()
}


fun loadBMIResults(context: Context): List<ResultBMI> {

    val sharedPreferences = context.getSharedPreferences(CONST_NAME, Context.MODE_PRIVATE)
    val json = sharedPreferences.getString(CONST_KEY, null)
    val gson = Gson()
    val type = object : TypeToken<List<ResultBMI>>() {}.type

    return gson.fromJson(json, type) ?: emptyList()
}
