package be.technifutur.devmob9.projet_cantinapp_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.repositories.MenusRepository

class MenusViewModel(private val menusRepository: MenusRepository): ViewModel() {

    fun getStartersData(): LiveData<MutableList<DishesType.Starters>> {
        val mutableData = MutableLiveData<MutableList<DishesType.Starters>>()
        menusRepository.getStarterMenus().observeForever {
            mutableData.value = it
        }
        return mutableData
    }

    fun getMainCoursesData(): LiveData<MutableList<DishesType.MainCourses>> {
        val mutableLiveData = MutableLiveData<MutableList<DishesType.MainCourses>>()
        menusRepository.getMainCourseMenus().observeForever {
            mutableLiveData.value = it
        }
        return mutableLiveData
    }

    fun getDessertsData(): LiveData<MutableList<DishesType.Desserts>> {
        val mutableLiveData = MutableLiveData<MutableList<DishesType.Desserts>>()
        menusRepository.getDessertMenus().observeForever {
            mutableLiveData.value = it
        }
        return mutableLiveData
    }

    fun onRetrievedMenuFromDate(date: String){
        menusRepository.onRetrievedMenusFromDate(date)
    }

    fun onRetrievedMenuData() = menusRepository.onRetrievedMenuData()
}