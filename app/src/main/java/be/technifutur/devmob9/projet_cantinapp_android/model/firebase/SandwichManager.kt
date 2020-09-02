package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import be.technifutur.devmob9.projet_cantinapp_android.model.data.FoodModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Sandwich
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_SANDWICHES
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.*

class SandwichManager {

    private val db = FirebaseFirestore.getInstance()
    fun onFetchingSandwichFromDate(date: String, onComplete: (List<Sandwich?>) -> Unit) {
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    return@addSnapshotListener
                }
                documentSnapshot?.let {
                    val foodModel = it.toObject<FoodModel>()
//                    foodModel?.sandwiches?.forEach { fetchingSandwiches(it, onComplete) }
                    foodModel?.sandwiches?.forEach { FoodManager.fetchingFood<Sandwich>(db, ID_SANDWICHES, it, onComplete) }
                }
            }
    }
//    private fun fetchingSandwiches(sandwichName: String, onComplete: (List<Sandwich>) -> Unit) {
//        val sandwichList = ArrayList<Sandwich>()
//        db.collection(ID_SANDWICHES)
//            .document(sandwichName)
//            .addSnapshotListener { documentSnapshot, e ->
//                e?.let {
//                    return@addSnapshotListener
//                }
//                documentSnapshot?.let {
//                    val sandwichData = it.toObject<Sandwich>()
//                    if(sandwichData != null) sandwichList.add(sandwichData)
//                }
//                onComplete(sandwichList)
//            }
//    }
}