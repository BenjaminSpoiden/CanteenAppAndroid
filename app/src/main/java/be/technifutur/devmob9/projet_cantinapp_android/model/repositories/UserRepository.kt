package be.technifutur.devmob9.projet_cantinapp_android.model.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource

class UserRepository(private val firebaseSource: FirebaseSource) {

    fun login(email: String, password: String) = firebaseSource.login(email, password)
    fun register(email: String, password: String) = firebaseSource.register(email, password)

    fun currentUser() = firebaseSource.currentUser()
    fun logout() = firebaseSource.logout()
}