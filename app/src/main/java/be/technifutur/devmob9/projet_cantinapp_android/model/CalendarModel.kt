package be.technifutur.devmob9.projet_cantinapp_android.model

import java.util.*

data class CalendarModel(var dayName: String?, val dayNumber: String?) {

    val calendar = GregorianCalendar()
}