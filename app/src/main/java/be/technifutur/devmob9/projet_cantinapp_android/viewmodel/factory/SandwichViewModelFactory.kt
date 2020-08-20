package be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.SandwichManager
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.SandwichViewModel

class SandwichViewModelFactory(private val sandwichManager: SandwichManager): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SandwichViewModel(sandwichManager) as T
    }
}