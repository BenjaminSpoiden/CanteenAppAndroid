package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.ArrayList

class FoodManager private constructor(){
    companion object {
        inline fun <reified M> fetchingFood(db: FirebaseFirestore, foodID: String, foodType: String, crossinline onComplete: (List<M?>) -> Unit) {
            val dishesData = ArrayList<M>()
            db.collection(foodID)
                .document(foodType)
                .addSnapshotListener { value, error ->
                    error?.let {
                        Log.d(FIREBASE_TAG, "${error.cause}")
                        return@addSnapshotListener
                    }
                    value?.let {
                        val dishesTypeModel = it.toObject<M>()
                        if(dishesTypeModel != null) {
                            dishesData.clear()
                            dishesData.add(dishesTypeModel)
                        }
                    }
                    onComplete.invoke(dishesData)
                }
        }
    }
}