package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.data.FoodModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Others
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_OTHERS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.ArrayList

class OthersManager {
    private val db = FirebaseFirestore.getInstance()

    fun onFetchingOthersFromDate(date: String, onComplete: (List<Others>) -> Unit) {
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    Log.d(FIREBASE_TAG, "${e.message}")
                    return@addSnapshotListener
                }

                documentSnapshot?.let {
                    val othersModel = it.toObject<FoodModel>()
                    othersModel?.others?.forEach {
                        fetchingOthers(it, onComplete)
                    }
                }
            }
    }

    private fun fetchingOthers(othersName: String, onComplete: (List<Others>) -> Unit) {
        val othersData = ArrayList<Others>()
        db.collection(ID_OTHERS)
            .document(othersName)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    Log.d(FIREBASE_TAG, "${e.message}")
                    return@addSnapshotListener
                }

                documentSnapshot?.let {
                    val othersModel = it.toObject<Others>()
                    if(othersModel != null) othersData.add(othersModel)
                }
                onComplete(othersData)
            }
    }
}