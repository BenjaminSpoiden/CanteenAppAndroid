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
    fun onFetchingSandwichFromDate(date: String, onComplete: (List<Sandwich?>) -> Unit, areSandwichAvailable: (List<String>?) -> Unit) {
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                firebaseFirestoreException?.let {
                    return@addSnapshotListener
                }
                documentSnapshot?.let {
                    val foodModel = it.toObject<FoodModel>()
                    foodModel?.sandwiches?.forEach { FoodManager.fetchingFood<Sandwich>(db, ID_SANDWICHES, it, onComplete) }
                    areSandwichAvailable(foodModel?.sandwiches)
                }
            }
    }
}