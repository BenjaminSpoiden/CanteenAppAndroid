package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType

class DateViewModel: ViewModel() {

    private val _dishesData = MutableLiveData<DishesType>()
    val getDishes: LiveData<DishesType>
        get() = _dishesData

    fun getDishes(dishesType: DishesType){
        _dishesData.value = dishesType
    }
}