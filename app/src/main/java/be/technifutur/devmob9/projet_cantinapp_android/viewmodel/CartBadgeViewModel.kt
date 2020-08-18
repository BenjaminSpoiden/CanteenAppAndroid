package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food
import java.util.ArrayList

class CartBadgeViewModel : ViewModel() {

    private val _foodItems = MutableLiveData<List<Food>>()
    val foodItems: LiveData<List<Food>>
        get() = _foodItems

    private val foodList = ArrayList<Food>()

    fun onAddingMenuItem(food: Food){
        foodList.add(food)
        _foodItems.value = foodList
    }

    fun onDeleteMenuItem(food: Food){
        foodList.remove(food)
        _foodItems.value = foodList
    }
}

