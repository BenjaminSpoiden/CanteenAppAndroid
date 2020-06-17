package be.technifutur.devmob9.projet_cantinapp_android.view.adapter

import android.view.View
import android.widget.TextView
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem

class CalendarItemAdapter(val calendarModel: CalendarModel): AbstractItem<CalendarItemAdapter.CalendarViwHolder>() {

    override val layoutRes: Int
        get() = R.layout.calendar_item
    override val type: Int
        get() = R.id.calendar_item_id

    override fun getViewHolder(v: View): CalendarViwHolder {
        return CalendarViwHolder(v)
    }

    inner class CalendarViwHolder(view: View): FastAdapter.ViewHolder<CalendarItemAdapter>(view) {
        private val calendarDayName = view.findViewById<TextView>(R.id.day_tv)
        private val calendarDayNumber = view.findViewById<TextView>(R.id.day_number_tv)

        override fun bindView(item: CalendarItemAdapter, payloads: List<Any>) {
            calendarDayName.text = item.calendarModel.dayName
            calendarDayNumber.text = item.calendarModel.dayNumber
        }

        override fun unbindView(item: CalendarItemAdapter) {
            calendarDayName.text = null
            calendarDayNumber.text = null
        }
    }
}