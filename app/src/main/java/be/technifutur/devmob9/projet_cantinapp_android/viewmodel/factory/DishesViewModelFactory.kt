package be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.DishesManager
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.DishesViewModel

class DishesViewModelFactory(private val dishesManager: DishesManager): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DishesViewModel(dishesManager) as T
    }
}