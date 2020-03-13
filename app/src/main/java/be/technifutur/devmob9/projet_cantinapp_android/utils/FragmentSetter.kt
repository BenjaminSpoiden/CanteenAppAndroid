package be.technifutur.devmob9.projet_cantinapp_android.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


//Permet de rajouter des functions utilitaire à la classe AppCompatActivity()
fun AppCompatActivity.addFragment(fragment: Fragment, id: Int){
    supportFragmentManager.inTransaction {
        add(id, fragment)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, id: Int){
    supportFragmentManager.inTransaction {
        replace(id, fragment)
    }
}

inline fun FragmentManager.inTransaction(function: FragmentTransaction.() -> FragmentTransaction){
    beginTransaction().function().commit()
}
