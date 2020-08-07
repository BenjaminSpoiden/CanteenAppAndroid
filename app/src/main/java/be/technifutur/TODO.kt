package be.technifutur

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG

/**
 * Pour récupérer les données de firebase, peut-être faire une énum pour séparer les différentes types de données (entrée/plat/dessert) avec les différentes section
 * de la recyclerview
 */

//Enum ?
enum class DishesTypeEnum{
    STARTERS,
    MAIN_COURSES,
    DESSERT
}

// Sealed Class ?
sealed class DishesType {
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
    data class MainCourses(val someValue2: Any): DishesType()
    data class Desserts(val someValue3: Any): DishesType()
}
