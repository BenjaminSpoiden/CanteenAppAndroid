package be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.OthersManager
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.OthersViewModel

class OthersViewModelFactory(private val othersManager: OthersManager): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OthersViewModel(othersManager) as T
    }
}