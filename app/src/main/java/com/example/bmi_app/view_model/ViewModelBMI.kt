package com.example.bmi_app.view_model
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class ViewModelBMI : ViewModel() {
    private val _uiState = MutableStateFlow(ViewModelBMIUiState())
    val uiState: StateFlow<ViewModelBMIUiState> = _uiState.asStateFlow()


    fun update(bmi: Double, resultText: String, color : Int, isMetricUnits: Boolean) {
        _uiState.update { currentState: ViewModelBMIUiState ->
            currentState.copy(
                bmi = bmi,
                resultText = resultText,
                color = color,
                isMetricUnits = isMetricUnits,
            )
        }
    }
}