package be.technifutur.devmob9.projet_cantinapp_android.model.firebase

import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.PICTURES
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class PicturesManager {
    fun fetchDishesPictures(picturePath: String) = Firebase.storage.reference.child(PICTURES).child(picturePath)
}