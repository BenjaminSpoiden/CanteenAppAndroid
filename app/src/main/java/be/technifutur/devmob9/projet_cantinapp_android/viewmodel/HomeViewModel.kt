package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.MenusRepository

class HomeViewModel(private val menusRepository: MenusRepository): ViewModel() {

    fun getCalendarDaysData(): LiveData<MutableList<CalendarModel>> {
        val data = MutableLiveData<MutableList<CalendarModel>>()
        menusRepository.getCalendarDays().observeForever {
            data.value = it
        }
        return data
    }

    fun onCalendarReceived() = menusRepository.onRetrievedCalendar()
}