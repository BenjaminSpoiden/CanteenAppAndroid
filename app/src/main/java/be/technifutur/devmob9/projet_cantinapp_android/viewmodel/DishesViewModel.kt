package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.DishesManager

class DishesViewModel(private val dishesManager: DishesManager): ViewModel() {

    var fetchedDishes = MediatorLiveData<List<DishesType>>()

    fun fetchingDishes(date: String) {
        dishesManager.onFetchingDishesFromDate(date) {
            fetchedDishes.value = it
            fetchedDishes.removeSource(fetchedDishes)
        }
    }
}