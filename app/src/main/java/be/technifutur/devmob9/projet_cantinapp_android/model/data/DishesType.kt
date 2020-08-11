package be.technifutur.devmob9.projet_cantinapp_android.model.data

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG


enum class DishesTypes {
    STARTERS,
    MAIN_COURSES,
    DESSERTS
}

sealed class DishesType: Food() {

    data class Starters(
        val name: String? = null,
        val description: String? = null,
        val price: Int? = null,
        val energy: Int? = null,
        val sugar: Int? = null,
        val fat: Int? = null,
        val protein: Int? = null
    ): DishesType(){
        fun showData() {
            Log.d(FIREBASE_TAG, "data: name: $name, desc: $description, prix: $price, energie: $energy, sucre: $sugar, gras: $fat, proteins: $protein")
        }
    }
    data class MainCourses(val name: String? = null,
                           val description: String? = null,
                           val price: Int? = null,
                           val energy: Int? = null,
                           val sugar: Int? = null,
                           val fat: Int? = null,
                           val protein: Int? = null): DishesType()

    data class Desserts(val name: String? = null,
                        val description: String? = null,
                        val price: Int? = null,
                        val energy: Int? = null,
                        val sugar: Int? = null,
                        val fat: Int? = null,
                        val protein: Int? = null): DishesType()
}
