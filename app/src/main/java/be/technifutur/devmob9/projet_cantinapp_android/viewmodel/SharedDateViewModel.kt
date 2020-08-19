package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class SharedDateViewModel: ViewModel() {

    val sharedDateDate = MediatorLiveData<String>()

    fun onDateClick(onDateClick: String) {
        sharedDateDate.value = onDateClick
    }
}