package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import androidx.fragment.app.Fragment
import be.technifutur.devmob9.projet_cantinapp_android.model.data.Food


interface FragmentListener {
//    fun onFragmentSelectedFromMenu(position: Int)

//    fun getFragmentTitle(title: String)

    fun openMenuDetail(foodItem: Food)

    fun openCheckoutFragment()

    fun onProfileMenuSelection(selectedRowPosition: Int)

    fun openAllergiesFragment(fragment: Fragment)
}