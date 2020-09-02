@file:Suppress("IMPLICIT_CAST_TO_ANY")

package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.data.FoodModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenusModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DESSERTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_MAIN_COURSES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.ArrayList

class DishesManager {

    private val db = FirebaseFirestore.getInstance()

    fun onFetchingDishesFromDate(date: String, onComplete: (List<DishesType?>) -> Unit, areDishesAvailable: (MenusModel?) -> Unit){
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    return@addSnapshotListener
                }
                documentSnapshot?.let { data ->
                    val foodModel = data.toObject<FoodModel>()
                    foodModel?.menu?.starters?.forEach { FoodManager.fetchingFood<DishesType.Starters>(db, ID_STARTERS, it, onComplete) }
                    foodModel?.menu?.main_courses?.forEach { FoodManager.fetchingFood<DishesType.MainCourses>(db, ID_MAIN_COURSES, it, onComplete) }
                    foodModel?.menu?.desserts?.forEach { FoodManager.fetchingFood<DishesType.Desserts>(db, ID_DESSERTS, it, onComplete) }

                    areDishesAvailable(foodModel?.menu)
                }
            }
    }
}