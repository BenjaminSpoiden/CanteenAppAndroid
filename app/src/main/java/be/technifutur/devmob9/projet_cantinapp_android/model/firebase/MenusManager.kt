package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import com.google.firebase.firestore.FirebaseFirestore

class MenusManager private constructor(){

    private object HOLDER {
        val INSTANCE = MenusManager()
    }

    companion object {
        val getInstance: MenusManager by lazy {
            HOLDER.INSTANCE
        }

    }

    private val db = FirebaseFirestore.getInstance()
    
}