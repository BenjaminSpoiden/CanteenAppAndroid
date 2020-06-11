package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel

interface CalendarListener {
    fun onCalendarReceived(calendarModel: CalendarModel)
}