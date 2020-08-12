package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.MenusRepository

class MenusViewModel(private val menusRepository: MenusRepository): ViewModel() {
    fun onRetrievedMenuFromDate(date: String){
        menusRepository.onRetrievedMenusFromDate(date)
    }

    fun onRetrievedMenuData() = menusRepository.onRetrievedMenuData()
}