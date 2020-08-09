package be.technifutur.devmob9.projet_cantinapp_android.model.repositories

import be.technifutur.devmob9.projet_cantinapp_android.model.data.DishesType
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.MenusManager

class MenusRepository(private val menusManager: MenusManager) {

    fun getStarterMenus() = menusManager.getStarterMenus()
    fun getMainCourseMenus() = menusManager.getMainCourseMenus()
    fun getDessertMenus() = menusManager.getDessertMenus()

}