package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalendarClickVM: ViewModel() {

    private val _didClickOnCalendar = MutableLiveData<Boolean>()
    val didClickOnCalendar: LiveData<Boolean>
        get() = _didClickOnCalendar

    fun didClick(click: Boolean){
        _didClickOnCalendar.value = click
    }

}