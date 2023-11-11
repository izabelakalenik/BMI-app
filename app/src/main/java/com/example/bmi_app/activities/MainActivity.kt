package com.example.bmi_app.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.bmi_app.R
import com.example.bmi_app.units.Imperial
import com.example.bmi_app.units.Metric




class MainActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    private lateinit var labelH: TextView
    private lateinit var labelW: TextView

    private lateinit var inputH: EditText
    private lateinit var inputW: EditText

    private lateinit var resultBMI: TextView
    private lateinit var resultTextBMI: TextView

    private lateinit var buttonBMI: Button
    private lateinit var unitSpinner: Spinner

    private var isMetricUnits = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createVars()
        setActionBar()

        unitSpinner.onItemSelectedListener = createOnItemSelectedListener()

        buttonBMI.setOnClickListener {
            runBMI()
        }

        resultBMI.setOnClickListener {
            openResultActivity()
        }
    }

    private fun createVars(){
        toolbar = findViewById(R.id.toolbar)

        labelH = findViewById(R.id.labelH)
        labelW = findViewById(R.id.labelW)

        inputH = findViewById(R.id.inputH)
        inputW = findViewById(R.id.inputW)

        resultBMI = findViewById(R.id.resultBMI)
        resultTextBMI = findViewById(R.id.resultTextBMI)

        buttonBMI = findViewById(R.id.buttonBMI)
        unitSpinner = findViewById(R.id.unitSpinner)

    }

    private fun setActionBar(){
        val popupTheme = R.style.AppTheme_PopupMenu
        toolbar.popupTheme = popupTheme

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu_item -> {
                true
            }

            R.id.menu_history -> {
                openNewScreen(HistoryActivity::class.java)
                true
            }

            R.id.menu_author -> {
                openNewScreen(AuthorActivity::class.java)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openNewScreen(activity: Class<*>) {
        val intent = Intent(this, activity)
        startActivity(intent)
    }

    private fun createOnItemSelectedListener(): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                isMetricUnits = position == 0

                if (isMetricUnits) {
                    labelH.text = getString(R.string.height_metric_label)
                    labelW.text = getString(R.string.weight_metric_label)
                } else {
                    labelH.text = getString(R.string.height_imperial_label)
                    labelW.text = getString(R.string.weight_imperial_label)
                }

                inputH.text.clear()
                inputW.text.clear()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
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

        val color = getColor(this, bmi)
        val resultText = getResultText(this, bmi)

        val formattedBMI = String.format("%.2f", bmi)

        resultBMI.setTextColor(color)
        resultBMI.text = formattedBMI

        resultTextBMI.setTextColor(color)
        resultTextBMI.text = resultText

    }

    private fun openResultActivity(){
        val resultIntent = Intent(this, ResultActivity::class.java)

        resultIntent.putExtra("bmi", resultBMI.text)
        resultIntent.putExtra("resultText", resultTextBMI.text)
        resultIntent.putExtra("color", resultBMI.currentTextColor)

        startActivity(resultIntent)

    }

}
