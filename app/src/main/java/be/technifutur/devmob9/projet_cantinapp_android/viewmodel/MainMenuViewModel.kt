package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants

class MainMenuViewModel(private val userRepository: UserRepository): ViewModel() {

    fun testingFirebaseData() {
        Log.d(Constants.FIREBASE_TAG, "Clicked on Button")
//        userRepository.addDateToDB()
//        Log.d(Constants.FIREBASE_TAG, "Added data")
//        userRepository.getDateFromMeal()
    }
}