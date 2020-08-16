package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import java.util.ArrayList

class CartBadgeViewModel : ViewModel() {

    private val _foodItems = MutableLiveData<List<Food>>()
    private val foodList = ArrayList<Food>()
    val foodItems: LiveData<List<Food>>
        get() = _foodItems

    fun onAddingMenusItem(food: Food){
        foodList.add(food)
        _foodItems.value = foodList
    }
}

