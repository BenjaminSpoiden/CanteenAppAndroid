package be.technifutur.devmob9.projet_cantinapp_android.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.MenusManager
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.MenusRepository
import be.technifutur.devmob9.projet_cantinapp_android.viewmodel.MenusViewModel

class MenuVMFactory(private val menusRepository: MenusRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MenusViewModel(menusRepository) as T
    }
}