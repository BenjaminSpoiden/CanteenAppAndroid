package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.MenusRepository
import be.technifutur.devmob9.projet_cantinapp_android.utils.observeOnce
import org.threeten.bp.LocalDate

class HomeViewModel(private val menusRepository: MenusRepository): ViewModel() {

    fun getCalendarDaysData() = menusRepository.getCalendarDays()

    fun onRetrievedMenuFromDate(date: String) = menusRepository.onRetrievedMenusFromDate(date)

    fun onRetrievedMenuData() = menusRepository.onRetrievedMenuData()

}