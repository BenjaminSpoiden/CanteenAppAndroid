package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType

interface DishesListener {
    fun fetchingDishes(dishesType: DishesType)
}