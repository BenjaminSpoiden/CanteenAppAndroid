package be.technifutur.devmob9.projet_cantinapp_android.utils

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.utils.Constants.FIREBASE_TAG
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuOthersFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuRepasFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuSandwichFragment
import java.lang.Exception

private val menuRepasFragment = MenuRepasFragment.getInstance()
private val menuSandwichFragment = MenuSandwichFragment.getInstance()
private val menuOthersFragment = MenuOthersFragment.getInstance()

enum class BottomNavigationScreens(
    @IdRes val menuItemID: Int,
    @DrawableRes val menuIcon: Int,
    @StringRes val menuTitle: Int,
    val fragment: Fragment
) {
    MENU(R.id.repasFragment, R.drawable.ic_menu_repas, R.string.menu, menuRepasFragment),
    SANDWICH(R.id.sandwichFragment, R.drawable.ic_menu_sandwich, R.string.sandwich, menuSandwichFragment),
    OTHERS(R.id.othersFragment, R.drawable.ic_menu_croissant, R.string.autre, menuOthersFragment)
}

fun getMainScreen(menuItemID: Int): BottomNavigationScreens? {
    Log.d(FIREBASE_TAG, "mainScreenTop")
    for (bottomScreens in BottomNavigationScreens.values()) {
        if(bottomScreens.menuItemID == menuItemID) {
            Log.d(FIREBASE_TAG, "mainScreenMiddle")
            return bottomScreens
        }
    }
    Log.d(FIREBASE_TAG, "mainScreenBottom")
    return null
}