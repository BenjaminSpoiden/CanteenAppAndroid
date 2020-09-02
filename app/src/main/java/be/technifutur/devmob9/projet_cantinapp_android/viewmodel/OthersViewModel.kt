package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.OthersType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.OthersManager
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_CROISSANTS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DRESSINGS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_DRINKS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_FRUITS
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.ID_YOGHURTS

class OthersViewModel(private val othersManager: OthersManager): ViewModel() {

    var fetchedOthers = MediatorLiveData<List<OthersType?>>()
    var checkingEmpty = MediatorLiveData<List<String>>()

    fun fetchingOthers(date: String) = othersManager.onFetchingOthersFromDate(date, {
        fetchedOthers.value = it
        fetchedOthers.removeSource(fetchedOthers)
    }, {
        val othersName = arrayListOf<String>()
        if(it?.croissants.isNullOrEmpty()) othersName.add(ID_CROISSANTS)
        if(it?.dressings.isNullOrEmpty()) othersName.add(ID_DRESSINGS)
        if(it?.drinks.isNullOrEmpty()) othersName.add(ID_DRINKS)
        if(it?.fruit.isNullOrEmpty()) othersName.add(ID_FRUITS)
        if(it?.yoghurts.isNullOrEmpty()) othersName.add(ID_YOGHURTS)
        checkingEmpty.value = othersName
    })
}