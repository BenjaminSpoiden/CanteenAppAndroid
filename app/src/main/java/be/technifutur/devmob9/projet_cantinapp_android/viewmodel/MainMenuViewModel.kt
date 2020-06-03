package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.CalendarModel
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.FirebaseSource
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.UserRepository

class MainMenuViewModel(private val userRepository: UserRepository): ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    val calendarModel = CalendarModel("", "")
    @RequiresApi(Build.VERSION_CODES.O)
    fun testingFirebaseData() {
        Log.d(FirebaseSource.TAG, "Clicked on Button")
//        userRepository.addDateToDB()
        Log.d(FirebaseSource.TAG, "Added data")
        userRepository.getDateFromMeal()
    }
}