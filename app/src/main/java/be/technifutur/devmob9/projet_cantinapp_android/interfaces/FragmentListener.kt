package be.technifutur.devmob9.projet_cantinapp_android.interfaces

interface FragmentListener {
    fun onFragmentSelectedFromMenu(position: Int)

    fun getFragmentTitle(title: String)

    fun openDetailFragment()

    fun openCheckoutFragment()

    fun onProfileMenuSelection(selectedRowPosition: Int)
}