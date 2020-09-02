package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

class SharedDateViewModel: ViewModel() {

    val sharedDishesFromDateClick = MediatorLiveData<String>()

    fun onFetchingDishesFromDateClick(onDateClick: String) {
        sharedDishesFromDateClick.value = onDateClick
        sharedDishesFromDateClick.removeSource(sharedDishesFromDateClick)
    }
}