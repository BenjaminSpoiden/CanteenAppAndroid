@file:Suppress("IMPLICIT_CAST_TO_ANY")

package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.data.FoodModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenusModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Sandwich
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DESSERTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_MAIN_COURSES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_SANDWICHES
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.ArrayList

class DishesManager {

    private val db = FirebaseFirestore.getInstance()

    fun onFetchingDishesFromDate(date: String, onComplete: (List<DishesType?>) -> Boolean){
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    return@addSnapshotListener
                }
                documentSnapshot?.let {
                    val foodModel = it.toObject<FoodModel>()
                    foodModel?.menu?.starters?.forEach {
                        fetchingStarters(it, onComplete)
                    }
                    foodModel?.menu?.main_courses?.forEach {
                        fetchingMainCourses(it, onComplete)
                    }
                    foodModel?.menu?.desserts?.forEach {
                        fetchingDesserts(it, onComplete)
                    }
                }
            }
    }

    private fun fetchingStarters(starterName: String, onComplete: (List<DishesType?>) -> Boolean){
        val startersList = ArrayList<DishesType>()
        db.collection(ID_STARTERS)
            .document(starterName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val starterData = it.toObject<DishesType.Starters>()
                    if(starterData != null) startersList.add(starterData)
                }
                onComplete(startersList)
            }
    }

    private fun fetchingMainCourses(mainsName: String, onComplete: (List<DishesType?>) -> Boolean) {
        val mainsList = ArrayList<DishesType>()
        db.collection(ID_MAIN_COURSES)
            .document(mainsName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val mainsData = it.toObject<DishesType.MainCourses>()
                    if(mainsData != null) mainsList.add(mainsData)
                }
                onComplete(mainsList)
            }
    }

    private fun fetchingDesserts(dessertsName: String, onComplete: (List<DishesType?>) -> Boolean){
        val dessertList = ArrayList<DishesType>()
        db.collection(ID_DESSERTS)
            .document(dessertsName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    return@let
                }
                documentSnapshot?.let {
                    val dessertsData = it.toObject<DishesType.Desserts>()
                    if(dessertsData != null) dessertList.add(dessertsData)
                }
                onComplete(dessertList)
            }
    }
}