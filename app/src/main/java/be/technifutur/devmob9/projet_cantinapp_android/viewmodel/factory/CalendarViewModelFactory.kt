package be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.CalendarDayManager
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.HomeViewModel


class CalendarViewModelFactory(private val calendarDayManager: CalendarDayManager): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(calendarDayManager) as T
    }
}