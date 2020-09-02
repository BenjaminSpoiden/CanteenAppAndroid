package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.DishesManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DESSERTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_MAIN_COURSES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS

class DishesViewModel(private val dishesManager: DishesManager): ViewModel() {

    var fetchedDishes = MediatorLiveData<List<DishesType?>>()
    var checkingEmpty = MediatorLiveData<List<String>>()


    fun fetchingDishes(date: String) {
        dishesManager.onFetchingDishesFromDate(date, {
            fetchedDishes.value = it
            fetchedDishes.removeSource(fetchedDishes)
        }, {
            val foodName = arrayListOf<String>()
            if(it?.starters.isNullOrEmpty()) {
                foodName.add(ID_STARTERS)
            }
            if(it?.main_courses.isNullOrEmpty()){
                foodName.add(ID_MAIN_COURSES)
            }
            if(it?.desserts.isNullOrEmpty()){
                foodName.add(ID_DESSERTS)
            }
            checkingEmpty.value = foodName
        })
    }
}