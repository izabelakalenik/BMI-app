package com.example.bmi_app.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bmi_app.R
import com.example.bmi_app.units.Imperial
import com.example.bmi_app.units.Metric


class MainActivity : AppCompatActivity() {

    private lateinit var labelH: TextView
    private lateinit var labelW: TextView
    private lateinit var inputH: EditText
    private lateinit var inputW: EditText
    private lateinit var resultBMI: TextView
    private lateinit var buttonBMI: Button
    private lateinit var unitSpinner: Spinner
    private var isMetricUnits = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createVars()

        unitSpinner.onItemSelectedListener = createOnItemSelectedListener()

        buttonBMI.setOnClickListener {
            runBMI()
        }

        resultBMI.setOnClickListener {
            openResultActivity()
        }

    }

    private fun createVars(){
        labelH = findViewById(R.id.labelH)
        labelW = findViewById(R.id.labelW)
        inputH = findViewById(R.id.inputH)
        inputW = findViewById(R.id.inputW)
        resultBMI = findViewById(R.id.resultBMI)
        buttonBMI = findViewById(R.id.buttonBMI)
        unitSpinner = findViewById(R.id.unitSpinner)

    }

   private fun createOnItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                isMetricUnits =
                    position == 0 // Jeśli wybrano "Metric", użyj jednostek metrycznych, w przeciwnym razie jednostek imperialnych
                labelH.text = getString(R.string.height_imperial_label)
                labelW.text = getString(R.string.weight_imperial_label)
                // Wyczyść pola wprowadzania
                inputH.text.clear()
                inputW.text.clear()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Nie rób nic
            }
        }
    }

    private fun runBMI(){
        val heightStr = inputH.text.toString()
        val weightStr = inputW.text.toString()

        if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
            val height = heightStr.toDouble()
            val weight = weightStr.toDouble()

            val calculatedBMI = if (isMetricUnits) {
                val metricInstance = Metric(height, weight)
                metricInstance.calculateBMI()

            } else {
                val imperialInstance = Imperial(height, weight)
                imperialInstance.calculateBMI()
            }

           displayBMI(calculatedBMI)
        }
        else {
            resultBMI.text = getString(R.string.input_error)
        }
      }


    private fun displayBMI(bmi: Double){

        val resultText = getResultColor(bmi)
        val formattedBMI = String.format("%.2f", bmi)

        resultBMI.text = getString(R.string.bmi_result, formattedBMI)
        resultBMI.append("\n$resultText")

    }

    private fun getResultColor(bmi: Double) : String{

        val resultText = when {
            bmi < 16.0 -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                "Underweight"
            }
            bmi in 16.0..16.99 -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.orange))
                "Severe Thinness"
            }
            bmi in 17.0..18.49 -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.yellow))
                "Mild Thinness"
            }
            bmi in 18.5..24.99 -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.green))
                "Normal Weight"
            }
            bmi in 25.0..29.9 -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.orange))
                "Overweight"
            }
            bmi in (30.0..34.99) -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                "Obesity Class I"
            }
            bmi in (35.0..39.99) -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                "Obesity Class II"
            }
            else -> {
                resultBMI.setTextColor(ContextCompat.getColor(this, R.color.red))
                "Obesity Class III (Morbidly Obese)"
            }
        }
        return resultText
    }

    private fun openResultActivity(){
        val resultIntent = Intent(this, ResultActivity::class.java)

//        resultIntent.putExtra("bmi", )
//        resultIntent.putExtra("resultText", resultText)

        startActivity(resultIntent)

    }

}
