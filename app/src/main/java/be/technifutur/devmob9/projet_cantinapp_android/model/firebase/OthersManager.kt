package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.data.FoodModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersType
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_CROISSANTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DAYS_MEALS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DRESSINGS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DRINKS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_FRUITS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_YOGHURTS
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.ArrayList

class OthersManager{
    private val db = FirebaseFirestore.getInstance()

    fun onFetchingOthersFromDate(date: String, onComplete: (List<OthersType?>) -> Unit, areOthersAvailable: (OthersModel?) -> Unit) {
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    Log.d(FIREBASE_TAG, "${e.message}")
                    return@addSnapshotListener
                }
                documentSnapshot?.let { docData ->
                    val othersModel = docData.toObject<FoodModel>()
                    othersModel?.others?.croissants?.forEach { FoodManager.fetchingFood<OthersType.Croissants>(db, ID_CROISSANTS, it, onComplete) }
                    othersModel?.others?.dressings?.forEach { FoodManager.fetchingFood<OthersType.Dressings>(db, ID_DRESSINGS, it, onComplete) }
                    othersModel?.others?.drinks?.forEach { FoodManager.fetchingFood<OthersType.Drinks>(db, ID_DRINKS, it, onComplete) }
                    othersModel?.others?.fruit?.forEach { FoodManager.fetchingFood<OthersType.Fruits>(db, ID_FRUITS, it, onComplete) }
                    othersModel?.others?.yoghurts?.forEach { FoodManager.fetchingFood<OthersType.Yoghurts>(db, ID_YOGHURTS, it, onComplete) }

                    areOthersAvailable(othersModel?.others)
                }
            }
    }
}