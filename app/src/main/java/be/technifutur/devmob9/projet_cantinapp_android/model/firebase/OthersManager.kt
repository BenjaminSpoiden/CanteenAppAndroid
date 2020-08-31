package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.model.data.FoodModel
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

    fun onFetchingOthersFromDate(date: String, onComplete: (List<OthersType?>) -> Unit) {
        db.collection(ID_DAYS_MEALS)
            .document(date)
            .addSnapshotListener { documentSnapshot, e ->
                e?.let {
                    Log.d(FIREBASE_TAG, "${e.message}")
                    return@addSnapshotListener
                }
                documentSnapshot?.let { docData ->
                    val othersModel = docData.toObject<FoodModel>()
                    othersModel?.others?.croissants?.forEach { fetchingOthers<OthersType.Croissants>(ID_CROISSANTS, it, onComplete) }
                    othersModel?.others?.dressings?.forEach { fetchingOthers<OthersType.Dressings>(ID_DRESSINGS, it, onComplete) }
                    othersModel?.others?.drinks?.forEach { fetchingOthers<OthersType.Drinks>(ID_DRINKS, it, onComplete) }
                    othersModel?.others?.fruit?.forEach { fetchingOthers<OthersType.Fruits>(ID_FRUITS, it, onComplete) }
                    othersModel?.others?.yoghurts?.forEach { fetchingOthers<OthersType.Yoghurts>(ID_YOGHURTS, it, onComplete) }
                }
            }
    }

    private inline fun <reified M> fetchingOthers(otherID: String, otherType: String, crossinline onComplete: (List<M?>) -> Unit) {
        val othersData = ArrayList<M>()
        db.collection(otherID)
            .document(otherType)
            .addSnapshotListener { value, error ->
                error?.let {
                    return@addSnapshotListener
                }
                value?.let {
                    val otherTypeModel = it.toObject<M>()
                    if(otherTypeModel != null) othersData.add(otherTypeModel)
                }
                onComplete(othersData)
            }
    }
}