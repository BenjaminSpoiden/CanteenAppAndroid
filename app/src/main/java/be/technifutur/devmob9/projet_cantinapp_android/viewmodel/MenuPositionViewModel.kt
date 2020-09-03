package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG

class MenuPositionViewModel: ViewModel() {

    val menuPosition = MediatorLiveData<Int>().apply {
        this.value = 0
    }

    fun getMenuPosition(position: Int) {
        Log.d(FIREBASE_TAG, "from vm: $position")
        menuPosition.value = position
    }
}