package be.technifutur.devmob9.projet_cantinapp_android.model.repositories


import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.CalendarDayManager
import be.technifutur.devmob9.projet_cantinapp_android.model.firebase.MenusManager

class MenusRepository(private val menusManager: MenusManager, private val calendarDayManager: CalendarDayManager) {

    fun getStarterMenus() = menusManager.getStarterMenus()
    fun getMainCourseMenus() = menusManager.getMainCourseMenus()
    fun getDessertMenus() = menusManager.getDessertMenus()

    fun getCalendarDays() = calendarDayManager.getCalendarDays()


    fun onRetrievedMenusFromDate(date: String) = menusManager.onRetrievedMenusFromDate(date)

    fun onRetrievedMenuData() = menusManager.mutableMainCoursesData

}