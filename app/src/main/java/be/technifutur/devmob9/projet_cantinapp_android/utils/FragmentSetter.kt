package be.technifutur.devmob9.projet_cantinapp_android.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import be.technifutur.devmob9.projet_cantinapp_android.R
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuOthersFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuRepasFragment
import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.MenuSandwichFragment

/**
 * Permet de rajouter des functions utilitaire à la classe [AppCompatActivity]
 */
fun AppCompatActivity.addFragment(fragment: Fragment, id: Int){
    supportFragmentManager.inTransaction {
        add(id, fragment)
    }
}

inline fun FragmentManager.inTransaction(function: FragmentTransaction.() -> FragmentTransaction){
    beginTransaction().function().commit()
}

fun Fragment.fragmentTransaction(fragment: Fragment, id: Int){
    val transaction: FragmentTransaction? = parentFragmentManager.beginTransaction()
    transaction?.replace(id, fragment)?.commit()
}


