package be.technifutur.devmob9.projet_cantinapp_android.model.data

import org.threeten.bp.LocalDate


data class CalendarModel(
    val dayName: String? = null,
    val dayNumber: String? = null,
    val date: LocalDate? = null,
    val isWorkday: Boolean? = false
)
