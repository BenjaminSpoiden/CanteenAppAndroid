package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import be.technifutur.devmob9.projet_cantinapp_android.view.adapter.menus.MenuChildAdapter

interface FragmentListener {
    fun onFragmentSelectedFromMenu(position: Int)

    fun getFragmentTitle(title: String)

    fun openDetailFragment()

    fun onDetailFragmentClick(holder: MenuChildAdapter.MenuChildViewHolder)

    fun openCheckoutFragment()

    fun onProfileMenuSelection(selectedRowPosition: Int)
}