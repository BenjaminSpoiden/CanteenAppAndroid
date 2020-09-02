package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Sandwich
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.SandwichManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import kotlin.math.log

class SandwichViewModel(private val sandwichManager: SandwichManager): ViewModel() {

    var fetchedSandwiches = MediatorLiveData<List<Sandwich?>>()

    fun fetchingSandwiches(date: String) = sandwichManager.onFetchingSandwichFromDate(date) {
        fetchedSandwiches.value = it
        fetchedSandwiches.removeSource(fetchedSandwiches)
    }
}