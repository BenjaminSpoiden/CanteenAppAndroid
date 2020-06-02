package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository

class MainMenuViewModel(private val userRepository: UserRepository): ViewModel() {

    fun testingFirebaseData() {
        Log.d("FirebaseTest", "Clicked on Button")
        userRepository.getDateFromMeal()
    }
}