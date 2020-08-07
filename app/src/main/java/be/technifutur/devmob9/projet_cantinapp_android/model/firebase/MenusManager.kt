package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import android.util.Log
import be.technifutur.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_STARTERS
import be.technifutur.devmob9.projet_cantinapp_android.utils.InvalidDocument
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MenusManager private constructor(){

    private object Holder {
        val instance = MenusManager()
    }

    companion object {
        val INSTANCE: MenusManager by lazy {
            Holder.instance
        }
    }

    private val db = FirebaseFirestore.getInstance()
    private var data: DishesType.Starters? = null

    init {
        getFirebaseStartersMenu()
    }

    private fun getFirebaseStartersMenu(): DishesType.Starters? {
        db.collection(ID_STARTERS).document("soupe_brocoli")
            .get()
            .addOnSuccessListener {
                data = it.toObject<DishesType.Starters>()
                Log.d(FIREBASE_TAG, db.collection(ID_STARTERS).id)
                Log.d(FIREBASE_TAG, "${it.data}")
                Log.d(FIREBASE_TAG, "specific: ${it.data?.get("prices")}")
                Log.d(FIREBASE_TAG, "keys ${it.data?.keys}")
                Log.d(FIREBASE_TAG, "values ${it.data?.values}")
                data?.showData()

            }
            .addOnFailureListener {
                Log.d(FIREBASE_TAG, "${it.cause}")
            }

        return data ?: throw InvalidDocument("Document doesn't exist")
    }
    
}