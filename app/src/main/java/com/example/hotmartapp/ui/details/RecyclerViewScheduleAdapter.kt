package com.example.hotmartapp.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotmartapp.R
import com.example.hotmartapp.data.model.Day

class RecyclerViewScheduleAdapter(
    private val dayList: ArrayList<Day>
) : RecyclerView.Adapter<RecyclerViewScheduleAdapter.ScheduleHolder>() {

    class ScheduleHolder(val view: View): RecyclerView.ViewHolder(view) {
        val nameDayTextView = view.findViewById<TextView>(R.id.nameDayTextView)
        val openHourTextView = view.findViewById<TextView>(R.id.openHourTextView)
        val closeHourTextView = view.findViewById<TextView>(R.id.closeHourTextView)

        fun bind(day: Day) {
            nameDayTextView.text = day.name + ":"
            openHourTextView.text = day.open + " às"
            closeHourTextView.text = day.close
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder =
        ScheduleHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false))

    override fun getItemCount(): Int = dayList.size

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        holder.bind(dayList[position])
    }
}