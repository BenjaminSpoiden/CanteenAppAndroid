package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Others
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.OthersManager

class OthersViewModel(private val othersManager: OthersManager): ViewModel() {

    var fetchedOthers = MediatorLiveData<List<OthersType?>>()

    fun fetchingOthers(date: String) = othersManager.onFetchingOthersFromDate(date) {
        fetchedOthers.value = it
        fetchedOthers.removeSource(fetchedOthers)
    }
}