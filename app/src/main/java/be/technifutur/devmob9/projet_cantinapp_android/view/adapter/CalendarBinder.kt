package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.colorSelection
import com.google.android.material.card.MaterialCardView
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class CalendarBinder: ItemBinder<CalendarModel, CalendarBinder.CalendarViewHolder>() {

    override fun bindViewHolder(holder: CalendarViewHolder?, item: CalendarModel?) {
        holder?.calendarDayName?.text = item?.dayName
        holder?.calendarDayNumber?.text = item?.dayNumber

        item?.isWorkday?.let {
            if(!it) {
                holder?.calendarCard?.alpha = 0.5F
                holder?.calendarCard?.isEnabled = false
            }
        }
        if(holder!!.isItemSelected) {
            Log.d("MultiView", "${item?.dayName}")
            setCardState(holder.calendarCard, holder.calendarDayName, holder.calendarDayNumber, true)
            holder.calendarCard.isEnabled = false
        }else {
            setCardState(holder.calendarCard, holder.calendarDayName, holder.calendarDayNumber, false)
            holder.calendarCard.isEnabled = true
        }
    }

    private fun setCardState(materialCardView: MaterialCardView, dayName: TextView, dayNumber: TextView, boolean: Boolean){
        if(boolean) {
            materialCardView.setCardBackgroundColor(colorSelection("#DBF9D8"))
            dayName.setTextColor(colorSelection("#2F4858"))
            dayNumber.setTextColor(colorSelection("#2F4858"))
        }else {
            materialCardView.setCardBackgroundColor(colorSelection("#FFFFFF"))
            dayName.setTextColor(colorSelection("#37BA89"))
            dayNumber.setTextColor(colorSelection("#37BA89"))
        }
    }

    override fun canBindData(item: Any?): Boolean {
        return item is CalendarModel
    }

    override fun createViewHolder(parent: ViewGroup?): CalendarViewHolder {
        val view = parent?.let {
            inflate(it, R.layout.calendar_item)
        }

        return CalendarViewHolder(view!!)
    }

    inner class CalendarViewHolder(view: View): ItemViewHolder<CalendarModel>(view){
        val calendarDayName: TextView = view.findViewById(R.id.day_tv)
        val calendarDayNumber: TextView = view.findViewById(R.id.day_number_tv)
        val calendarCard: MaterialCardView = view.findViewById(R.id.calendar_card_view)

        init {
            calendarCard.setOnClickListener {
                Log.d("MultiView", "test")
                toggleItemSelection()
            }
        }
    }
}