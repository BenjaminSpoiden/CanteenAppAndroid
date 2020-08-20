package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class SharedDateViewModel: ViewModel() {

    val sharedDate = MediatorLiveData<String>()

    fun onSharedDateClick(onDateClick: String) {
        sharedDate.value = onDateClick
        sharedDate.removeSource(sharedDate)
    }
}