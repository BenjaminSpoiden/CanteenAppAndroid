package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import kotlinx.android.extensions.LayoutContainer

class CalendarAdapter(options: FirestoreRecyclerOptions<CalendarModel>) : FirestoreRecyclerAdapter<CalendarModel, CalendarAdapter.CalendarViewHolder>(options) {


    inner class CalendarViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        val day: TextView = containerView.findViewById(R.id.day_tv)
        val dayNumber: TextView = containerView.findViewById(R.id.day_number_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_item, parent, false)
        return CalendarViewHolder(view)
    }

//    override fun getItemCount(): Int = calendarModelList.size
//
//    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
//        val calendarItem = calendarModelList[position]
//        holder.day.text = calendarItem.dayName
//        holder.dayNumber.text = calendarItem.dayNumber
//    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int, model: CalendarModel) {
        holder.apply {
            day.text = model.dayName
            dayNumber.text = model.dayNumber
        }
    }

}