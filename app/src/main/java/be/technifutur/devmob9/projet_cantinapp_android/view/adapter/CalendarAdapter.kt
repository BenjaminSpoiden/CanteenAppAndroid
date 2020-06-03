package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel

class CalendarAdapter(private val calendarModelList: List<CalendarModel>): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {


    inner class CalendarViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val day: TextView = view.findViewById(R.id.day_tv)
        val dayNumber: TextView = view.findViewById(R.id.day_number_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return CalendarViewHolder(view)
    }

    override fun getItemCount(): Int = calendarModelList.size

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val calendarItem = calendarModelList[position]
        holder.day.text = calendarItem.dayName
        holder.dayNumber.text = calendarItem.dayNumber
    }
}