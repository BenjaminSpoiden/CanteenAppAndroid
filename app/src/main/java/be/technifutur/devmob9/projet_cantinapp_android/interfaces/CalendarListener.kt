package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarItemAdapter

interface CalendarListener {
    fun onCalendarReceived(calendarModel: CalendarModel)
}