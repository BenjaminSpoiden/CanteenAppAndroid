package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import be.technifutur.devmob9.projet_cantinapp_android.model.data.MenuItemModel


interface FragmentListener {
    fun onFragmentSelectedFromMenu(position: Int)

//    fun getFragmentTitle(title: String)

    fun openDetailFragment()

    fun openDetailFragmentWithDetails(itemClicked: MenuItemModel)

    fun openCheckoutFragment()

    fun onProfileMenuSelection(selectedRowPosition: Int)
}