package com.example.bmi_app.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi_app.R
import com.example.bmi_app.history.HistoryAdapter
import com.example.bmi_app.history.loadBMIResults


class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        createVars()
    }

    private fun createVars(){
        recyclerView = findViewById(R.id.recyclerViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        historyAdapter = HistoryAdapter(loadBMIResults(this), this)
        recyclerView.adapter = historyAdapter
    }

}
