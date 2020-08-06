package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import com.google.android.material.card.MaterialCardView
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class CalendarItem(val calendarModel: CalendarModel): AbstractItem<CalendarItem.CalendarViwHolder>() {

    override val layoutRes: Int
        get() = R.layout.calendar_item
    override val type: Int
        get() = R.id.calendar_item_id


    override fun getViewHolder(v: View): CalendarViwHolder {
        return CalendarViwHolder(v)
    }

    inner class CalendarViwHolder(view: View): FastAdapter.ViewHolder<CalendarItem>(view) {
        private val calendarDayName = view.findViewById<TextView>(R.id.day_tv)
        private val calendarDayNumber = view.findViewById<TextView>(R.id.day_number_tv)
        val calendarCard = view.findViewById<MaterialCardView>(R.id.calendar_card_view)

        override fun bindView(item: CalendarItem, payloads: List<Any>) {
            calendarDayName.text = item.calendarModel.dayName
            calendarDayNumber.text = item.calendarModel.dayNumber
            calendarCard.isEnabled = item.calendarModel.isWorkday!!
            item.calendarModel.isWorkday?.let {
                if(!it) {
                    calendarCard.alpha = 0.5F
                }
            }
        }

        override fun unbindView(item: CalendarItem) {
            calendarDayName.text = null
            calendarDayNumber.text = null
        }
    }
}