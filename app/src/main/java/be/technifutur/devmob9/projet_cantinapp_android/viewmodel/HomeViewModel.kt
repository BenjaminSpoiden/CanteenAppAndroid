package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.*
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.CalendarDayManager

class HomeViewModel(private val calendarDayManager: CalendarDayManager): ViewModel() {
    val fetchedDates = MediatorLiveData<List<CalendarModel>>()
    fun getCalendarDaysData() = calendarDayManager.fetchCalendarDays {
        fetchedDates.value = it
        fetchedDates.removeSource(fetchedDates)
    }
}