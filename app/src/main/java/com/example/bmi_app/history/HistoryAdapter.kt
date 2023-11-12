package com.example.bmi_app.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi_app.R
import com.example.bmi_app.ResultBMI
import com.example.bmi_app.activities.getColor

class HistoryAdapter(private val bmiResults: List<ResultBMI>, private val context: Context) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val heightTextView: TextView = itemView.findViewById(R.id.heightTextView)
        val weightTextView: TextView = itemView.findViewById(R.id.weightTextView)
        val bmiValueTextView: TextView = itemView.findViewById(R.id.bmiValueTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bmiResult = bmiResults[position]

        holder.dateTextView.text = bmiResult.date
        if (bmiResult.units == "metric"){
            holder.heightTextView.text = context.getString(R.string.height_metric, bmiResult.height.toString())
            holder.weightTextView.text = context.getString(R.string.weight_metric, bmiResult.weight.toString())
        }
        else{
            holder.heightTextView.text = context.getString(R.string.height_imperial, bmiResult.height.toString())
            holder.weightTextView.text = context.getString(R.string.weight_imperial, bmiResult.weight.toString())
        }

        val color = getColor(context, bmiResult.bmiValue)
        holder.bmiValueTextView.text = context.getString(R.string.history_bmi, String.format("%.2f", bmiResult.bmiValue))
        holder.bmiValueTextView.setTextColor(color)

    }

    override fun getItemCount(): Int {
        return bmiResults.size
    }

}
