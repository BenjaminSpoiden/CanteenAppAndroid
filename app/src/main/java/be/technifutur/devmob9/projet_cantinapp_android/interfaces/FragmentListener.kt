package be.technifutur.devmob9.projet_cantinapp_android.interfaces

import be.technifutur.devmob9.projet_cantinapp_android.view.ui.fragments.DetailsFragment

interface FragmentListener {
    fun onFragmentSelectedFromMenu(position: Int)

    fun getFragmentTitle(title: String)

    fun openDetailFragment()
}