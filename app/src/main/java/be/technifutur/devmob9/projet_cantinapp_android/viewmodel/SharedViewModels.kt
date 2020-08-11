package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.threeten.bp.LocalDate

class SharedViewModels: ViewModel() {

    val dateSelected = MutableLiveData<LocalDate>()

    fun selectDate(date: LocalDate){
        dateSelected.value = date
    }
}