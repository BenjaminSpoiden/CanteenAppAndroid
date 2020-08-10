package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import org.threeten.bp.LocalDate

interface DayListener {
    fun onDayListener(date: LocalDate?)
}