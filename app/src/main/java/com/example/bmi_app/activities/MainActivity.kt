package com.example.bmi_app.activities
import com.example.bmi_app.view_model.ViewModelBMI
import com.example.bmi_app.view_model.ViewModelBMIUiState
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
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.bmi_app.R
import kotlinx.coroutines.launch


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


        val viewModel: ViewModelBMI by viewModels()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {uiState ->
                    uiState.bmi
                    uiState.resultText
                    uiState.color
                    uiState.isMetricUnits
                    if(viewModel.uiState.value.bmi != null) {
                        displayBMI(viewModel.uiState.value)
                    }
                }
            }
        }

        createVars()
        setActionBar()

        unitSpinner.onItemSelectedListener = createOnItemSelectedListener()

        buttonBMI.setOnClickListener {
            updateBMI(viewModel)
            displayBMI(viewModel.uiState.value)

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
    private fun displayBMI(uiState: ViewModelBMIUiState){

        val formattedBMI = String.format("%.2f", uiState.bmi)

        resultBMI.setTextColor(uiState.color)
        resultBMI.text = formattedBMI

        resultTextBMI.setTextColor(uiState.color)
        resultTextBMI.text = uiState.resultText

    }
    private fun updateBMI(viewModel: ViewModelBMI){
        val bmi = getBMI(this, inputH.text.toString(), inputW.text.toString(), isMetricUnits)
        val color = getColor(this, bmi)
        val resultText = getResultText(this, bmi)

        viewModel.update(bmi, resultText, color, isMetricUnits)
    }


    private fun openResultActivity(){
        val resultIntent = Intent(this, ResultActivity::class.java)

        resultIntent.putExtra("bmi", resultBMI.text)
        resultIntent.putExtra("resultText", resultTextBMI.text)
        resultIntent.putExtra("color", resultBMI.currentTextColor)

        startActivity(resultIntent)

    }

}
