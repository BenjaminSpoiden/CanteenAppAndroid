package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenusModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.DishesManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG

class DishesViewModel(private val dishesManager: DishesManager): ViewModel() {

    var fetchedDishes = MediatorLiveData<List<DishesType?>>()
    var isStarterEmpty = MediatorLiveData<Boolean>()
    var isMainCoursesEmpty = MediatorLiveData<Boolean>()
    var isDessertEmpty = MediatorLiveData<Boolean>()

    fun fetchingDishes(date: String) {
        dishesManager.onFetchingDishesFromDate(date, {
            fetchedDishes.value = it
            fetchedDishes.removeSource(fetchedDishes)
        }, {
            isStarterEmpty.value = it?.starters.isNullOrEmpty()
            isStarterEmpty.removeSource(isStarterEmpty)

            isMainCoursesEmpty.value = it?.main_courses.isNullOrEmpty()
            isMainCoursesEmpty.removeSource(isMainCoursesEmpty)

            isDessertEmpty.value = it?.desserts.isNullOrEmpty()
            isDessertEmpty.removeSource(isDessertEmpty)
        })
    }
}