package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.interfaces.DayListener
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository
import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.CalendarBinder

class HomeViewModel(private val userRepository: UserRepository): ViewModel() {

    var dayOfWeek = MutableLiveData<String>()
}